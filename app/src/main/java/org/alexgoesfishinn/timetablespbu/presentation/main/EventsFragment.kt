package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DaysAdapter



class EventsFragment : Fragment(R.layout.events_fragment) {
    private var binding: EventsFragmentBinding? = null
    private val args: EventsFragmentArgs by navArgs()
    private lateinit var groupId: String
    private lateinit var noEventsCardView: View
    private lateinit var weekEventsNavPanelView: View
    private lateinit var service: EventsService
    private lateinit var weekDisplayTextView: TextView
    private lateinit var previousWeekButton: Button
    private lateinit var nextWeekButton: Button
    private lateinit var daysRecycler: RecyclerView
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var manager: LayoutManager



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventsFragmentBinding.bind(view)
        groupId = args.groupdId
        noEventsCardView = view.findViewById(R.id.noEvents)
        weekEventsNavPanelView = view.findViewById(R.id.weekEventsNavPanel)
        service = RetrofitService.eventsService
        weekDisplayTextView = view.findViewById(R.id.currentWeekText)
        nextWeekButton = view.findViewById(R.id.nextWeekButton)
        previousWeekButton = view.findViewById(R.id.previousWeekButton)
        daysRecycler = view.findViewById(R.id.eventsRecycler)
        manager = LinearLayoutManager(requireContext())
        daysRecycler.layoutManager = manager
        daysRecycler.adapter = DaysAdapter(emptyList())
        TODO("добавить отображение названия группы")
        getCurrentWeekEvents()
    }

    private fun enableRecycler(groupEvents: GroupEvents) {
        daysRecycler.visibility = View.VISIBLE
        val days = groupEvents.days
        daysAdapter = DaysAdapter(days)
        daysRecycler.apply {
            adapter = daysAdapter
            layoutManager = manager
        }
    }

    private fun disableRecycler() {
        daysRecycler.visibility = View.GONE
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
            noEventsCardView.visibility = View.VISIBLE
            disableRecycler()
        } else {
            noEventsCardView.visibility = View.GONE
            enableRecycler(groupEvents)
        }
        nextWeekButton.setOnClickListener {
            getAnotherWeekEvents(groupEvents.nextWeekMonday)
        }
        previousWeekButton.setOnClickListener {
            getAnotherWeekEvents(groupEvents.previousWeekMonday)
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