package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Program
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination

class ProgramCombinationsAdapter(
    private val data: List<ProgramCombination>,
    private val programCombinationsClickListener: ProgramCombinationsClickListener
): RecyclerView.Adapter<ProgramCombinationsAdapter.ProgramCombinationsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgramCombinationsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.programs_combinations_recycler_item, parent, false)
        return ProgramCombinationsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProgramCombinationsViewHolder, position: Int) {
        data[position].let {
            holder.programCombinationName.text = it.programName
            val programs = it.programs
            holder.itemView.setOnClickListener {
                programCombinationsClickListener.onClick(programs)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class ProgramCombinationsViewHolder(itemView: View): ViewHolder(itemView){
        val programCombinationName: TextView = itemView.findViewById(R.id.programCombinationName)
    }
}
interface ProgramCombinationsClickListener {
    fun onClick(programs: List<Program>)
}