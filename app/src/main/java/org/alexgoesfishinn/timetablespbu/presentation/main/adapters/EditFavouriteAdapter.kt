package org.alexgoesfishinn.timetablespbu.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.model.FavouriteItem

class EditFavouriteAdapter(
    private val editFavouriteRemoveClickListener: EditFavouriteRemoveClickListener
): Adapter<EditFavouriteAdapter.EditFavouriteViewHolder>() {
    var data: List<FavouriteItem> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditFavouriteViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.edit_favourite_item, parent, false)
        return EditFavouriteViewHolder(item)
    }

    override fun onBindViewHolder(holder: EditFavouriteViewHolder, position: Int) {
        data[position].let {
            holder.groupName.text = it.displayName
            val id = it.id
            holder.remove.setOnClickListener {
                editFavouriteRemoveClickListener.onClick(id)
            }
        }
    }

    override fun getItemCount() = data.size

    class EditFavouriteViewHolder(itemView: View): ViewHolder(itemView){
        val groupName:TextView = itemView.findViewById(R.id.edit_favorite_item_group_name)
        val remove: TextView = itemView.findViewById(R.id.edit_favourite_item_remove)
    }
}

interface EditFavouriteRemoveClickListener {
    fun onClick(id: Long)
}