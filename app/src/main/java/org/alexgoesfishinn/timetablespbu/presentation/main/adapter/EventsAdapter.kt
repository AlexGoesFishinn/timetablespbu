package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
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
        val eventLecturer: TextView = itemView.findViewById(R.id.eventLecturer)
        val subgroup: TextView = itemView.findViewById(R.id.eventSubgroup)
        val subgroupIcon: ImageView = itemView.findViewById(R.id.eventSubgroupIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.event_recycler_item, parent, false)
        return EventsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val terraCotColor = holder.itemView.context.getColor(R.color.spbu_primary_terracot_color)
        data[position].let {
            val eventLocations = it.eventLocations
            val educators = it.educatorIds
            holder.eventTime.text = it.timeIntervalString
            holder.eventName.text = it.subject

            holder.subgroup.visibility = View.GONE
            holder.subgroupIcon.visibility = View.GONE
            if(eventLocations.size == 1 && educators.size == 1){
                holder.eventPlace.text = eventLocations[0].displayName
                holder.eventLecturer.text = it.educatorDisplayText
                }
            else{
                val spanLocationText = SpannableString(eventLocations[0].displayName)
                val spanEducatorText = SpannableString(it.educatorDisplayText)
                spanLocationText.setSpan(UnderlineSpan(), 0, spanLocationText.length, 0)
                spanEducatorText.setSpan(UnderlineSpan(), 0 , spanEducatorText.length, 0)
                holder.eventPlace.text = spanEducatorText
                holder.eventLecturer.text = spanEducatorText
                holder.eventPlace.setTextColor(terraCotColor)
                holder.eventLecturer.setTextColor(terraCotColor)
                holder.eventPlace.setOnClickListener {
                    showEventLocationsDialog(eventLocations)
                }
                holder.eventLecturer.setOnClickListener {
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