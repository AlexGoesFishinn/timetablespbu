package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Event
import org.alexgoesfishinn.timetablespbu.domain.entities.EventLocation
import org.alexgoesfishinn.timetablespbu.presentation.main.EventLocationsDialog

class EventsAdapter(
    private val data: List<Event>
): Adapter<EventsAdapter.EventsViewHolder>(){
    private lateinit var context: Context
    class EventsViewHolder(itemView: View): ViewHolder(itemView){
        val eventTime: TextView = itemView.findViewById(R.id.eventTime)
        val eventName: TextView = itemView.findViewById(R.id.eventName)
        val eventPlace: TextView = itemView.findViewById(R.id.eventPlace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.event_recycler_item, parent, false)
        return EventsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        data[position].let {
            val eventLocations = it.eventLocations
            holder.eventTime.text = it.timeIntervalString
            holder.eventName.text = it.subject
            if(eventLocations.size == 1){holder.eventPlace.text = eventLocations[0].displayName}
            else{
                holder.eventPlace.text = eventLocations[0].displayName + " ..."
                holder.eventPlace.setOnClickListener {
                    showEventLocationsDialog(eventLocations)
                }
                //TODO("implement dialog with eventLocations recyclerview")


            }
        }
    }

    private fun showEventLocationsDialog(eventLocations: List<EventLocation>){
        val eventLocationsDialog = EventLocationsDialog(context, eventLocations)
        eventLocationsDialog.show()
    }
}