package org.alexgoesfishinn.timetablespbu.presentation.main.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.presentation.main.model.GroupItem

/**
 * @author a.bylev
 */
class GroupsAdapter(
    private val groupsClickListener: GroupsClickListener
):RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {

    var data:List<GroupItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.groups_recycler_item, parent, false)
        return GroupsViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        data[position].let {
            val groupName = it.groupName
            holder.groupName.text = groupName
            val groupId = it.groupId
            holder.itemView.setOnClickListener {
                Log.i("GroupId", groupId.toString())
                groupsClickListener.onClick(groupId)
            }

        }
    }

    class GroupsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val groupName: TextView = itemView.findViewById(R.id.group_name)
    }
}
interface GroupsClickListener{
    fun onClick(groupId: Long)
}