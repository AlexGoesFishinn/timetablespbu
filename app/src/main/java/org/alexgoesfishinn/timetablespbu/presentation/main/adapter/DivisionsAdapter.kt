package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Division

class DivisionAdapter(
    private val data: List<Division>,
    private val listener: DivisionClickListener): RecyclerView.Adapter<DivisionAdapter.DivisionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DivisionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.division_recycler_item, parent, false)
        return DivisionViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DivisionViewHolder, position: Int) {
        data[position].let {
            holder.divisionName.text = it.name
            val alias: String = it.alias
            holder.itemView.setOnClickListener{
                listener.onItemClick(alias)
                Log.e("ALIAS", alias)

            }
        }

    }

    class DivisionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val divisionName: TextView = itemView.findViewById(R.id.divisionName)
    }


}

interface DivisionClickListener{
    fun onItemClick(alias: String)
}