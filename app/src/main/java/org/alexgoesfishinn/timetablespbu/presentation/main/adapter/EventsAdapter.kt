package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Event

class EventsAdapter(
    private val data: List<Event>
): Adapter<EventsAdapter.EventsViewHolder>(){

    class EventsViewHolder(itemView: View): ViewHolder(itemView){
        val eventTime: TextView = itemView.findViewById(R.id.eventTime)
        val eventName: TextView = itemView.findViewById(R.id.eventName)
        val eventPlace: TextView = itemView.findViewById(R.id.eventPlace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.event_recycler_item, parent, false)
        return EventsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        data[position].let {
            holder.eventTime.text = it.start
            holder.eventName.text = it.subject
            holder.eventPlace.text = it.eventLocations[0].displayName
        }
    }
}