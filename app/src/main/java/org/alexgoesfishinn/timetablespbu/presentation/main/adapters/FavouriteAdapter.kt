package org.alexgoesfishinn.timetablespbu.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.model.FavouriteItem
/**
 * @author a.bylev
 */
class FavouriteAdapter(
    private val navigationListener: FavouriteNavigateClickListener,
    private val removeClickListener: FavouriteRemoveClickListener
): Adapter<FavouriteAdapter.FavouriteViewHolder>() {



        var data: List<FavouriteItem> = emptyList()
            set(newValue) {
                field = newValue
                notifyDataSetChanged()
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.favourite_item, parent, false)
        return FavouriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        data[position].let {
            holder.text.text = it.displayName
            val id = it.id
            holder.text.setOnClickListener {
                navigationListener.onClick(id)
            }

            holder.remove.setOnClickListener {
                removeClickListener.onClick(id)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class FavouriteViewHolder(itemView: View): ViewHolder(itemView){
        val text: TextView = itemView.findViewById(R.id.favorite_item_group_name)
        val remove: TextView = itemView.findViewById(R.id.favourite_item_remove)
    }
}

interface FavouriteNavigateClickListener{
    fun onClick(id: Long)
}

interface FavouriteRemoveClickListener {
    fun onClick(id: Long)
}