package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.databinding.EventsFragmentBinding
import org.alexgoesfishinn.timetablespbu.di.RetrofitService
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DaysAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EventsFragment: Fragment(R.layout.events_fragment) {
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
        weekEventsNavPanelView= view.findViewById(R.id.weekEventsNavPanel)
        service = RetrofitService.eventsService
        weekDisplayTextView = view.findViewById(R.id.currentWeekText)

        nextWeekButton = view.findViewById(R.id.nextWeekButton)

        previousWeekButton = view.findViewById(R.id.previousWeekButton)
        daysRecycler = view.findViewById(R.id.eventsRecycler)
        manager = LinearLayoutManager(requireContext())
        getCurrentWeekEvents()
    }

    private fun enableRecycler(groupEvents: GroupEvents){
        daysRecycler.visibility = View.VISIBLE
        val days = groupEvents.days
        daysAdapter = DaysAdapter(days)
        daysRecycler.apply {
            adapter = daysAdapter
            layoutManager = manager
        }
    }

    private fun disableRecycler(){
        daysRecycler.visibility = View.GONE
    }

    private fun onSuccessResponce(groupEvents: GroupEvents){
        weekDisplayTextView.text = groupEvents.weekDisplayText


        if(!groupEvents.isPreviousWeekReferenceAvailable){
            previousWeekButton.visibility = View.GONE
        } else previousWeekButton.visibility = View.VISIBLE
        if(!groupEvents.isNextWeekReferenceAvailable){
            nextWeekButton.visibility = View.GONE
        } else nextWeekButton.visibility = View.VISIBLE
        if(groupEvents.days.isEmpty()){
            noEventsCardView.visibility = View.VISIBLE
            disableRecycler()
        } else {
            noEventsCardView.visibility = View.GONE
            enableRecycler(groupEvents)
        }


        nextWeekButton.setOnClickListener {
            Log.i("NextWeekButton", groupEvents.weekMonday)
            getAnotherWeekEvents(groupEvents.nextWeekMonday)
        }
        previousWeekButton.setOnClickListener {

            Log.i("PreviuosWeekButton", groupEvents.previousWeekMonday)
            getAnotherWeekEvents(groupEvents.previousWeekMonday)
        }

    }

    private fun getCurrentWeekEvents(){

        service.getEventsForCurrentWeek(groupId).enqueue(object : Callback<GroupEvents> {
            override fun onResponse(call: Call<GroupEvents>, response: Response<GroupEvents>) {
                if (response.isSuccessful){

                    val groupEvents = response.body()
                    if(groupEvents != null){
                        onSuccessResponce(groupEvents)
                    }
                }
            }

            override fun onFailure(call: Call<GroupEvents>, t: Throwable) {
                Log.e(TAG, "сетевая ошибка ${t.message}")
            }

        })
    }

    private fun getAnotherWeekEvents(from: String){
        service.getEventsForNotCurrentWeek(groupId, from).enqueue(object: Callback<GroupEvents>{
            override fun onResponse(call: Call<GroupEvents>, response: Response<GroupEvents>) {
                if(response.isSuccessful){
                    val groupEvents = response.body()
                    if(groupEvents != null){
                        onSuccessResponce(groupEvents)
                    }

                }
            }

            override fun onFailure(call: Call<GroupEvents>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    private companion object{
        private const val TAG = "EventsFragment"
    }
}