package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Division

/**
 * @author a.bylev
 */
class DivisionsAdapter(
    private val data: List<Division>,
    private val listener: DivisionsClickListener): RecyclerView.Adapter<DivisionsAdapter.DivisionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DivisionsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.division_recycler_item, parent, false)
        return DivisionsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DivisionsViewHolder, position: Int) {
        data[position].let {
            val name = it.name
            holder.divisionName.text = name
            val alias: String = it.alias
            holder.itemView.setOnClickListener{
                listener.onItemClick(alias, name)
                Log.e("ALIAS", alias)

            }
        }

    }

    class DivisionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val divisionName: TextView = itemView.findViewById(R.id.divisionName)
    }


}

interface DivisionsClickListener{
    fun onItemClick(alias: String, name: String)
}