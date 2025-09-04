package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.EventLocation

/**
 * @author a.bylev
 */
class EventLocationAdapter(
    private var data: List<EventLocation>
): Adapter<EventLocationAdapter.EventLocationViewHolder>() {

    private lateinit var locationEducators: List<LocationEducator>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventLocationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.eventlocations_recycler_item, parent, false)
        Log.i("EventLocationAdapter", "creating ViewHolder")

        return EventLocationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        locationEducators = flatEventLocationList(data)
        return locationEducators.size
    }

    override fun onBindViewHolder(holder: EventLocationViewHolder, position: Int) {
        Log.i("List", locationEducators.toString())
        locationEducators[position].let {
            holder.eventLocationName.text = it.location
            holder.eventEducatorName.text = it.educator
            Log.i("List", it.location)
            Log.i("List", it.educator)
        }
//        data[position].let {
//            Log.i("EventLocationAdapter", "I'm here")
//            Log.i("EventLocationAdapter", it.displayName)
//            holder.eventLocationName.text = it.displayName
//        }
//        Log.i("BindViewHolder", data.size.toString())
//        Log.i("BindViewHolder", data.toString())
    }

    private fun flatEventLocationList(eventLocations: List<EventLocation>): List<LocationEducator>{
        val list: MutableList<LocationEducator> = mutableListOf()
        eventLocations.forEach {l -> l.educatorIds.forEach {
            e -> list.add(LocationEducator(l.displayName, e.item2))
        }}
        return list
    }

    class EventLocationViewHolder(itemView: View): ViewHolder(itemView){
        val eventLocationName: TextView = itemView.findViewById(R.id.eventLocationLocationName)
        val eventEducatorName: TextView = itemView.findViewById(R.id.eventLocationEducatorName)
    }

    data class LocationEducator(
        val location: String,
        val educator: String
    )
}