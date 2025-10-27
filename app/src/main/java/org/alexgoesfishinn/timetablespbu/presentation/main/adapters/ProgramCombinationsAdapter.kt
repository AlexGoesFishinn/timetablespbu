package org.alexgoesfishinn.timetablespbu.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramCombinationItem

/**
 * @author a.bylev
 */
class ProgramCombinationsAdapter(
    private val programCombinationsClickListener: ProgramCombinationsClickListener
): RecyclerView.Adapter<ProgramCombinationsAdapter.ProgramCombinationsViewHolder>() {

    var data: List<ProgramCombinationItem> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgramCombinationsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.programs_combinations_recycler_item, parent, false)
        return ProgramCombinationsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProgramCombinationsViewHolder, position: Int) {
        data[position].let {
            val programName = it.programName
            holder.programCombinationName.text = programName
            val programCombinationId = it.programCombinationId
            holder.itemView.setOnClickListener {
                programCombinationsClickListener.onClick(programCombinationId, programName)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class ProgramCombinationsViewHolder(itemView: View): ViewHolder(itemView){
        val programCombinationName: TextView = itemView.findViewById(R.id.program_combination_name)
    }
}
interface ProgramCombinationsClickListener {
    fun onClick(programCombinationId: Long, programName: String)
}