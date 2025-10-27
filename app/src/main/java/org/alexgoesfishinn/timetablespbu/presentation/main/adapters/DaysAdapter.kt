package org.alexgoesfishinn.timetablespbu.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.card.MaterialCardView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.model.DayItem

/**
 * @author a.bylev
 */
class DaysAdapter(
    private val listener: DaysClickListener
): Adapter<DaysAdapter.DaysViewHolder>() {

    private var index = -1
    var data: List<DayItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


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
            val dayId = it.id
            holder.itemView.setOnClickListener {
                listener.onItemClick(dayId, holder.adapterPosition)
                index = holder.adapterPosition
                notifyDataSetChanged()
            }
            if(index == holder.adapterPosition){
                holder.dayCard.setCardBackgroundColor(terraCotColor)
                holder.dayName.setTextColor(whiteColor)
                holder.dayDate.setTextColor(whiteColor)
            } else{
                holder.dayCard.setCardBackgroundColor(whiteColor)
                holder.dayName.setTextColor(terraCotColor)
                holder.dayDate.setTextColor(terraCotColor)
            }

        }

    }



    class DaysViewHolder(itemView: View): ViewHolder(itemView){
        val dayName: TextView = itemView.findViewById(R.id.day_name)
        val dayDate: TextView= itemView.findViewById(R.id.day_date)
        val dayCard: MaterialCardView = itemView.findViewById(R.id.day_card)

    }

}

interface DaysClickListener{
    fun onItemClick(dayId: Long, adapterPosition: Int)
}