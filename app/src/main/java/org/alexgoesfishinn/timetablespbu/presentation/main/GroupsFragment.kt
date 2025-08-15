package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.services.GroupsService
import org.alexgoesfishinn.timetablespbu.databinding.GroupsFragmentBinding
import org.alexgoesfishinn.timetablespbu.di.RetrofitService
import org.alexgoesfishinn.timetablespbu.domain.entities.Group
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramGroups

import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.GroupsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.GroupsClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GroupsFragment: Fragment(R.layout.groups_fragment) {
    private var binding: GroupsFragmentBinding? = null
    private val args: GroupsFragmentArgs by navArgs()
    private lateinit var groupsRecycler: RecyclerView
    private lateinit var groupsAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager
    private var groups: List<Group> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GroupsFragmentBinding.bind(view)
        val programId: String = args.programId
        manager = LinearLayoutManager(requireContext())
        groupsRecycler = view.findViewById(R.id.groupsRecycler)
        groupsRecycler.apply {
            layoutManager = manager
            adapter = GroupsAdapter(groups,object : GroupsClickListener{
                override fun onClick(groupId: Long) {
                    navigateToEvents(groupId)
                }
            })
        }
        if(groups.isEmpty()){
            getGroups(programId = programId)
        }

    }

    private fun navigateToEvents(groupId: Long){
        val groupIdString = groupId.toString()
        findNavController().navigate(GroupsFragmentDirections.actionGroupToEvents(groupIdString))
    }

    private fun getGroups(programId: String){
        val service = RetrofitService.groupsService
        lifecycleScope.launch {
            val response = service.getGroups(programId)
            if(response.isSuccessful){
                val data = response.body()
                if(data != null){
                    groups = data.groups
                    groupsAdapter = GroupsAdapter(groups, object: GroupsClickListener {
                        override fun onClick(groupId: Long) {
                            navigateToEvents(groupId)
                        }
                    })
                    groupsRecycler.apply {
                        adapter = groupsAdapter
                    }
                } else Log.e(TAG, "received data is null")
            } else Log.e(TAG, "response if not successful")
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private companion object{
        private const val TAG = "GroupsFragment"
    }
}
