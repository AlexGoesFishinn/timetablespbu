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
class MainFavouriteAdapter(
    private val navigateClickListener: NavigateClickListener
): Adapter<MainFavouriteAdapter.MainFavouriteViewHolder>() {

    var data: List<FavouriteItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFavouriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.main_fragment_recycler_item, parent, false)
        return MainFavouriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainFavouriteViewHolder, position: Int) {
        data[position].let {
            holder.item.text = it.displayName
            val id = it.id
            holder.item.setOnClickListener {
                navigateClickListener.onClick(id)
            }
        }
    }

    override fun getItemCount() = data.size

    class MainFavouriteViewHolder(itemView: View): ViewHolder(itemView){
        val item: TextView = itemView.findViewById(R.id.main_fragment_favourite_item_text)
    }
}

interface NavigateClickListener{
    fun onClick(id: Long)
}