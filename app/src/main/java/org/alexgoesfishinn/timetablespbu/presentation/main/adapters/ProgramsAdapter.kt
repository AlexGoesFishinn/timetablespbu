package org.alexgoesfishinn.timetablespbu.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramItem

/**
 * @author a.bylev
 */
class ProgramsAdapter(
    private val programClickListener: ProgramsClickListener
): RecyclerView.Adapter<ProgramsAdapter.ProgramsViewHolder>() {

    var data: List<ProgramItem> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.program_recycler_item, parent, false)
        return ProgramsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ProgramsViewHolder, position: Int) {
        data[position].let {
            val programYear = it.yearName
            holder.programYearName.text = programYear
            val programId = it.programId
            holder.itemView.setOnClickListener {
                programClickListener.onClick(programId, programYear)
            }
        }
    }

    class ProgramsViewHolder(itemView: View): ViewHolder(itemView){
        val programYearName: TextView = itemView.findViewById(R.id.year_name)
    }
}
interface ProgramsClickListener{
    fun onClick(programId: Long, programYear: String)
}