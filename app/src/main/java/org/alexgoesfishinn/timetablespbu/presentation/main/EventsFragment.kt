package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.databinding.EventsFragmentBinding
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventsFragment: Fragment(R.layout.events_fragment) {
    private var binding: EventsFragmentBinding? = null
    private val args: EventsFragmentArgs by navArgs()
    private lateinit var groupId: String
    private lateinit var noEventsCardView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventsFragmentBinding.bind(view)
        groupId = args.groupdId
        noEventsCardView = view.findViewById(R.id.noEvents)
        getEvents()
    }

    private fun getEvents(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(EventsService::class.java)
        service.getEventsForCurrentWeek(groupId).enqueue(object : Callback<GroupEvents> {
            override fun onResponse(call: Call<GroupEvents>, response: Response<GroupEvents>) {
                if (response.isSuccessful){
                    val groupEvents = response.body()
                    if(groupEvents!!.days.isEmpty()){

                        noEventsCardView.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<GroupEvents>, t: Throwable) {
                Log.e(TAG, "сетевая ошибка ${t.message}")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    private companion object{
        private const val BASE_URL = "https://timetable.spbu.ru/api/v1/"
        private const val TAG = "EventsFragment"
    }
}