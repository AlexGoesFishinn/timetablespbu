package org.alexgoesfishinn.timetablespbu.presentation.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.domain.entities.Group

class GroupsAdapter(
    private val data: List<Group>,
    private val groupsClickListener: GroupsClickListener
):RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.groups_recycler_item, parent, false)
        return GroupsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        data[position].let {
            holder.groupName.text = it.groupName
            val groupId = it.groupId
            holder.itemView.setOnClickListener {
                Log.i("GroupId", groupId.toString())
                groupsClickListener.onClick(groupId)
            }

        }
    }

    class GroupsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val groupName: TextView = itemView.findViewById(R.id.groupName)
    }
}
interface GroupsClickListener{
    fun onClick(groupId: Long)
}