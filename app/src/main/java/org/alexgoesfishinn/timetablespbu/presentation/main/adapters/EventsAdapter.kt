package org.alexgoesfishinn.timetablespbu.presentation.main.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.fragments.events.EventLocationsDialog
import androidx.core.net.toUri
import org.alexgoesfishinn.timetablespbu.presentation.main.model.EventItem
import org.alexgoesfishinn.timetablespbu.presentation.main.model.LocationItem
/**
 * @author a.bylev
 */
class EventsAdapter: Adapter<EventsAdapter.EventsViewHolder>(){
    var data: List<EventItem> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    private lateinit var context: Context

    class EventsViewHolder(itemView: View): ViewHolder(itemView){
        val eventTime: TextView = itemView.findViewById(R.id.event_time)
        val eventName: TextView = itemView.findViewById(R.id.event_name)
        val eventPlace: TextView = itemView.findViewById(R.id.event_place)
        val eventLecturer: TextView = itemView.findViewById(R.id.event_educator)
        val subgroup: TextView = itemView.findViewById(R.id.event_subgroup)
        val subgroupIcon: ImageView = itemView.findViewById(R.id.event_subgroup_icon)
        val eventPlaceIcon: ImageView = itemView.findViewById(R.id.event_place_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.event_recycler_item, parent, false)
        return EventsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val terraCotColor = holder.itemView.context.getColor(R.color.spbu_primary_terracot_color)
        val eventChangedColor = holder.itemView.context.getColor(R.color.spbu_event_changed_color)
        data[position].let {
            val eventLocations = it.locations
            holder.eventTime.text = it.timeIntervalString
            holder.eventName.text = it.subject

            holder.subgroup.visibility = View.GONE
            holder.subgroupIcon.visibility = View.GONE
            if(eventLocations.size == 1 && eventLocations[0].educators.size == 1){
                holder.eventPlace.text = eventLocations[0].displayName
                holder.eventLecturer.text = it.educatorDisplayText
                if(eventLocations[0].hasGeographicCoordinates){
                    val longitude = eventLocations[0].longitude
                    val latitude = eventLocations[0].latitude
                    holder.eventPlaceIcon.setImageResource(R.drawable.ic_location_hasgeo)
                    holder.eventPlaceIcon.setOnClickListener {

                        val ymIntentUri =
                            "https://yandex.ru/maps/?pt=$longitude,$latitude&z=18&l=map".toUri()
                        val mapIntent = Intent(Intent.ACTION_VIEW, ymIntentUri)
                        context.startActivity(mapIntent)

                    }
                }
                }
            else{
                val spanLocationText = SpannableString(eventLocations[0].displayName)
                val spanEducatorText = SpannableString(it.educatorDisplayText)
                spanLocationText.setSpan(UnderlineSpan(), 0, spanLocationText.length, 0)
                spanEducatorText.setSpan(UnderlineSpan(), 0 , spanEducatorText.length, 0)
                holder.eventPlace.text = spanLocationText
                holder.eventLecturer.text = spanEducatorText
                holder.eventPlace.setTextColor(terraCotColor)
                holder.eventLecturer.setTextColor(terraCotColor)

                holder.eventPlace.setOnClickListener {
                    showEventLocationsDialog(eventLocations)
                }
                holder.eventLecturer.setOnClickListener {
                    showEventLocationsDialog(eventLocations)
                }

            }
            if(it.isCancelled){
                holder.eventTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                holder.eventTime.setBackgroundColor(eventChangedColor)
                holder.eventName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                holder.eventName.setBackgroundColor(eventChangedColor)
                holder.eventPlace.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                holder.eventPlace.setBackgroundColor(eventChangedColor)
                holder.eventLecturer.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                holder.eventLecturer.setBackgroundColor(eventChangedColor)
                holder.subgroup.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                holder.subgroup.setBackgroundColor(eventChangedColor)

            }
            if(it.timeWasChanged){
                holder.eventTime.setBackgroundColor(eventChangedColor)
            }
            if(it.locationWasChanged){
                holder.eventPlace.setBackgroundColor(eventChangedColor)
            }
            if(it.educatorsWereReassigned){
                holder.eventLecturer.setBackgroundColor(eventChangedColor)
            }
        }
    }

    private fun showEventLocationsDialog(eventLocations: List<LocationItem>){
        val eventLocationsDialog = EventLocationsDialog(context, eventLocations)
        eventLocationsDialog.show()
    }
}