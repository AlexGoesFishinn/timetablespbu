package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Program

class ProgramsAdapter(
    private val data: List<Program>,
    private val programClickListener: ProgramsClickListener
): RecyclerView.Adapter<ProgramsAdapter.ProgramsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.program_recycler_item, parent, false)
        return ProgramsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ProgramsViewHolder, position: Int) {
        data[position].let {
            holder.programYearName.text = it.yearName
            val programId = it.programId
            holder.itemView.setOnClickListener {
                programClickListener.onClick(programId)
            }
        }
    }

    class ProgramsViewHolder(itemView: View): ViewHolder(itemView){
        val programYearName: TextView = itemView.findViewById(R.id.yearName)
    }
}
interface ProgramsClickListener{
    fun onClick(programId: Long)
}