package org.alexgoesfishinn.timetablespbu.presentation.main.adapter




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Day
import org.alexgoesfishinn.timetablespbu.domain.entities.Event

class DaysAdapter(
    private val data: List<Day>,
    private val listener: DaysClickListener
): Adapter<DaysAdapter.DaysViewHolder>() {

    private var index = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.days_recycler_item, parent, false)
        return DaysViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        val terraCotColor = holder.itemView.context.getColor(R.color.spbu_primary_terracot_color)
        val whiteColor = holder.itemView.context.getColor(R.color.white)
        data[position].let {
            val dayInfo = it.name.split(", ")
            holder.dayName.text = dayInfo[0]
            holder.dayDate.text = dayInfo[1]

            val events = it.events
            holder.itemView.setOnClickListener {
                listener.onItemClick(events)
                index = holder.adapterPosition
                notifyDataSetChanged()
            }
            if(index == holder.adapterPosition){
                holder.itemView.setBackgroundColor(terraCotColor)
                holder.dayName.setTextColor(whiteColor)
                holder.dayDate.setTextColor(whiteColor)
            } else{
                holder.itemView.setBackgroundColor(whiteColor)
                holder.dayName.setTextColor(terraCotColor)
                holder.dayDate.setTextColor(terraCotColor)
            }
        }
    }



    class DaysViewHolder(itemView: View): ViewHolder(itemView){
        val dayName: TextView = itemView.findViewById(R.id.dayName)
        val dayDate: TextView= itemView.findViewById(R.id.dayDate)
    }

}

interface DaysClickListener{
    fun onItemClick(events: List<Event>)
}