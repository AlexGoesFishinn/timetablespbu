package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Division

class DivisionAdapter(private val data: List<Division>): RecyclerView.Adapter<DivisionAdapter.DivisionViewHolder>() {

//    var data: List<Division> = emptyList()
//        set(newValue){
//            field = newValue
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DivisionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.division_layout, parent, false)
        return DivisionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DivisionViewHolder, position: Int) {
        data[position].let {
            holder.divisionName.text = it.name
        }

    }

    class DivisionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val divisionName: TextView = itemView.findViewById(R.id.divisionName)
    }
}