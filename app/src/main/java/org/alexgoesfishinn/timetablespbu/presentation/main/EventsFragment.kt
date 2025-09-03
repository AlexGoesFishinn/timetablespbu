package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.databinding.EventsFragmentBinding
import org.alexgoesfishinn.timetablespbu.di.RetrofitService
import org.alexgoesfishinn.timetablespbu.domain.entities.Event
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DaysAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DaysClickListener
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.EventsAdapter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: сохранять состояние страницы при смене ориентации

class EventsFragment : Fragment(R.layout.events_fragment) {
    private var binding: EventsFragmentBinding? = null
    private val args: EventsFragmentArgs by navArgs()
    private lateinit var groupId: String
    private lateinit var noEventsText: TextView
    private lateinit var weekEventsNavPanelView: View
    private lateinit var service: EventsService
    private lateinit var weekDisplayTextView: TextView
    private lateinit var previousWeekButton: TextView
    private lateinit var nextWeekButton: TextView
    private lateinit var daysRecycler: RecyclerView
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var daysManager: LayoutManager
    private lateinit var eventsRecycler: RecyclerView
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var eventsManager: LinearLayoutManager




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventsFragmentBinding.bind(view)
        groupId = args.groupdId
        noEventsText = view.findViewById(R.id.noEventsText)
        weekEventsNavPanelView = view.findViewById(R.id.weekEventsNavPanel)
        service = RetrofitService.eventsService
        weekDisplayTextView = view.findViewById(R.id.currentWeekText)
        nextWeekButton = view.findViewById(R.id.nextWeekButton)
        previousWeekButton = view.findViewById(R.id.previousWeekButton)
        eventsRecycler = view.findViewById(R.id.eventsRecycler)
        eventsRecycler.apply {
            eventsManager = LinearLayoutManager(requireContext())
            layoutManager = eventsManager
            eventsAdapter = EventsAdapter(emptyList())
            adapter = eventsAdapter
        }

        daysRecycler = view.findViewById(R.id.daysRecycler)
        daysManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        daysRecycler.layoutManager = daysManager
        daysRecycler.adapter = DaysAdapter(emptyList(), object : DaysClickListener{
            override fun onItemClick(events: List<Event>) {
                enableEventsRecycler(events)
            }

        })

//        TODO("добавить отображение названия группы")
        getCurrentWeekEvents()
    }

    private fun enableDaysRecycler(groupEvents: GroupEvents) {
        daysRecycler.visibility = View.VISIBLE
        val days = groupEvents.days
        daysAdapter = DaysAdapter(days, object : DaysClickListener{
            override fun onItemClick(events: List<Event>) {
                enableEventsRecycler(events)
            }

        })
        daysRecycler.apply {
            adapter = daysAdapter
            layoutManager = daysManager
        }
        val adapterPosition = getAdapterPosition(groupEvents)
        daysRecycler.post {
            daysRecycler.findViewHolderForAdapterPosition(adapterPosition)?.itemView?.performClick()
        }
    }

    private fun getAdapterPosition(groupEvents: GroupEvents): Int{
        var adapterPosition = 0
        if(!groupEvents.isCurrentWeekReferenceAvailable){
            // TODO: реализовать выбор текущего дня
            val days = groupEvents.days
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val currentDate = LocalDate.now()
            if(currentDate.dayOfWeek == DayOfWeek.SUNDAY){
                getAnotherWeekEvents(groupEvents.nextWeekMonday)
            }
            val currentDateString = currentDate.format(formatter).toString()
            for(i in days.indices){
                if(days[i].dateString.startsWith(currentDateString)){
                    adapterPosition = i
                }
            }
        }

        return adapterPosition
    }

    private fun enableEventsRecycler(events: List<Event>){
        eventsRecycler.visibility = View.GONE
        eventsRecycler.visibility = View.VISIBLE
        eventsRecycler.apply {
            eventsAdapter = EventsAdapter(events)
            adapter = eventsAdapter
        }

    }

    private fun disableDaysRecycler() {
        daysRecycler.visibility = View.GONE
    }

    private fun disableEventsRecycler(){
        eventsRecycler.visibility = View.GONE
    }

    private fun onSuccessResponse(groupEvents: GroupEvents) {
        weekDisplayTextView.text = groupEvents.weekDisplayText
        if (!groupEvents.isPreviousWeekReferenceAvailable) {
            previousWeekButton.visibility = View.GONE
        } else previousWeekButton.visibility = View.VISIBLE
        if (!groupEvents.isNextWeekReferenceAvailable) {
            nextWeekButton.visibility = View.GONE
        } else nextWeekButton.visibility = View.VISIBLE
        if (groupEvents.days.isEmpty()) {
            noEventsText.visibility = View.VISIBLE
            disableDaysRecycler()
        } else {
            noEventsText.visibility = View.GONE
            enableDaysRecycler(groupEvents)
        }
        nextWeekButton.setOnClickListener {
            getAnotherWeekEvents(groupEvents.nextWeekMonday)
            disableEventsRecycler()
        }
        previousWeekButton.setOnClickListener {
            getAnotherWeekEvents(groupEvents.previousWeekMonday)
            disableEventsRecycler()
        }

    }

    private fun getCurrentWeekEvents() {
        lifecycleScope.launch {
            val response = service.getEventsForCurrentWeek(groupId)
            if (response.isSuccessful) {
                val data = response.body()

                if (data != null) {
                    onSuccessResponse(data)
                } else Log.i(TAG, "CurrentWeekResponse data is null")
            } else Log.e(TAG, "CurrentWeekResponse is not successful")
        }



    }

    private fun getAnotherWeekEvents(from: String) {
        lifecycleScope.launch {
            val response = service.getEventsForNotCurrentWeek(groupId, from)
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    onSuccessResponse(data)
                } else Log.i(TAG, "NotCurrentWeekResponse data is null")
            } else Log.e(TAG, "NotCurrentWeekResponse is not successful")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    private companion object {
        private const val TAG = "EventsFragment"
    }
}