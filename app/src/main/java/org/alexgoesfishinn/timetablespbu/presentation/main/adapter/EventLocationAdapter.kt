package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.EventLocation

class EventLocationAdapter(
    private var data: List<EventLocation>
): Adapter<EventLocationAdapter.EventLocationViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventLocationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.eventlocations_dialog, parent, false)
        return EventLocationViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: EventLocationViewHolder, position: Int) {
        data[position].let {
            holder.eventLocationName.text = it.displayName
        }
    }

    class EventLocationViewHolder(itemView: View): ViewHolder(itemView){
        val eventLocationName: TextView = itemView.findViewById(R.id.eventLocationName)
    }
}