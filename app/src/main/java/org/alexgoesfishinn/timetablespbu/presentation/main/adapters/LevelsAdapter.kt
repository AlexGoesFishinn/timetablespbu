package org.alexgoesfishinn.timetablespbu.presentation.main.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.model.LevelItem

/**
 * @author a.bylev
 */
class LevelsAdapter(
    private val levelsClickListener: LevelsClickListener
):RecyclerView.Adapter<LevelsAdapter.LevelsViewHolder>() {

    var data: List<LevelItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }



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
            val levelId = it.levelId
            holder.itemView.setOnClickListener {
                levelsClickListener.onClick(levelId, level)
                Log.i("LEVELNAME", level)
            }
        }
    }

    class LevelsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val levelName: TextView = itemView.findViewById(R.id.level_name)
    }
}

interface LevelsClickListener{
    fun onClick(levelId: Long, levelName: String)
}