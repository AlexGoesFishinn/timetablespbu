package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination


class LevelsAdapter(
    private val data: List<Level>,
    private val levelsClickListener: LevelsClickListener
):RecyclerView.Adapter<LevelsAdapter.LevelsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.level_recycler_item, parent, false)
        return LevelsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: LevelsViewHolder, position: Int) {
        data[position].let {
            holder.levelName.text = it.levelName
            val level = it.levelName
            val programCombinations = it.programCombinations
            holder.itemView.setOnClickListener {

                levelsClickListener.onClick(programCombinations)
                Log.i("LEVELNAME", level )
            }
        }
    }

    class LevelsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val levelName: TextView = itemView.findViewById(R.id.levelName)
    }
}

interface LevelsClickListener{
    fun onClick(programCombinations: List<ProgramCombination>)
}