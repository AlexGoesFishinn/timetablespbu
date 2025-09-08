package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
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
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventLocationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.eventlocations_recycler_item, parent, false)
        Log.i("EventLocationAdapter", "creating ViewHolder")
        context = parent.context

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
            if(it.hasGeo){
                holder.eventPlaceIcon.setImageResource(R.drawable.ic_location_hasgeo)
                val latitude = it.latitude
                val longitude = it.longitude
                val ymIntentUri =
                    "https://yandex.ru/maps/?pt=$longitude,$latitude&z=18&l=map".toUri()
                val mapIntent = Intent(Intent.ACTION_VIEW, ymIntentUri)
                context.startActivity(mapIntent)
            }
            Log.i("List", it.location)
            Log.i("List", it.educator)
        }

    }

    private fun flatEventLocationList(eventLocations: List<EventLocation>): List<LocationEducator>{
        val list: MutableList<LocationEducator> = mutableListOf()
        eventLocations.forEach {l -> l.educatorIds.forEach {
            e -> list.add(LocationEducator(l.displayName, e.item2, l.latitude, l.longitude, l.hasGeographicCoordinates))
        }}
        return list
    }

    class EventLocationViewHolder(itemView: View): ViewHolder(itemView){
        val eventLocationName: TextView = itemView.findViewById(R.id.eventLocationLocationName)
        val eventEducatorName: TextView = itemView.findViewById(R.id.eventLocationEducatorName)
        val eventPlaceIcon: ImageView = itemView.findViewById(R.id.eventPlaceIcon)
    }

    data class LocationEducator(
        val location: String,
        val educator: String,
        val latitude: Double,
        val longitude: Double,
        val hasGeo: Boolean
    )
}