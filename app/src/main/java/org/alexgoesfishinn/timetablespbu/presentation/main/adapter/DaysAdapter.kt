package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Day
import org.alexgoesfishinn.timetablespbu.domain.entities.Event

class DaysAdapter(
    val data: List<Day>
): Adapter<DaysAdapter.DaysViewHolder>() {

    private lateinit var eventRecycler: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private lateinit var eventManager: LayoutManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.days_recycler_item, parent, false)
        eventRecycler = itemView.findViewById(R.id.event)
        eventRecycler.apply {
            eventManager = LinearLayoutManager(parent.context)
            layoutManager = eventManager
            adapter = EventAdapter(emptyList())
        }
        return DaysViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        data[position].let {
            holder.dayName.text = it.name
            val events = it.events
            holder.itemView.setOnClickListener {
                enableEventRecycler(events)
            }
        }
    }

    private fun enableEventRecycler(events: List<Event>){
        eventRecycler.visibility = View.GONE
        eventRecycler.visibility = View.VISIBLE
        eventRecycler.apply {
            eventAdapter = EventAdapter(events)
            adapter = eventAdapter
            layoutManager = eventManager
        }
    }

    class DaysViewHolder(itemView: View): ViewHolder(itemView){
        val dayName: TextView = itemView.findViewById(R.id.dayName)
    }

    class EventAdapter(
        private val data: List<Event>
    ): Adapter<EventAdapter.EventViewHolder>(){

        class EventViewHolder(itemView: View): ViewHolder(itemView){
            val eventTime: TextView = itemView.findViewById(R.id.eventTime)
            val eventName: TextView = itemView.findViewById(R.id.eventName)
            val eventPlace: TextView = itemView.findViewById(R.id.eventPlace)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.event_recycler_item, parent, false)
            return EventViewHolder(itemView)
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
            data[position].let {
                holder.eventTime.text = it.start
                holder.eventName.text = it.subject
                holder.eventPlace.text = it.eventLocations[0].displayName
            }
        }
    }
}