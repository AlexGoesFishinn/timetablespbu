package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.services.GroupsService
import org.alexgoesfishinn.timetablespbu.databinding.GroupsFragmentBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GroupsFragmentBinding.bind(view)
        val programId: String = args.programId
        manager = LinearLayoutManager(requireContext())
        groupsRecycler = view.findViewById(R.id.groupsRecycler)
        getGroups(programId = programId)
    }

    private fun navigateToEvents(groupId: Long){
        val groupIdString = groupId.toString()
        findNavController().navigate(GroupsFragmentDirections.actionGroupToEvents(groupIdString))
    }

    private fun getGroups(programId: String){
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GroupsService::class.java)
        service.getGroups(programId).enqueue(object: Callback<ProgramGroups> {
            override fun onResponse(call: Call<ProgramGroups>, response: Response<ProgramGroups>) {
                if(response.isSuccessful){
                    val programGroups = response.body()
                    val groups: List<Group> = programGroups!!.groups
                    groupsAdapter = GroupsAdapter(groups, object : GroupsClickListener {
                        override fun onClick(groupId: Long) {
                            navigateToEvents(groupId)
                        }

                    })
                    groupsRecycler.apply {
                        adapter = groupsAdapter
                        layoutManager = manager
                    }
                }
            }

            override fun onFailure(call: Call<ProgramGroups>, t: Throwable) {
                Log.e(TAG, "Сетевая ошибка ${t.message}")
            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private companion object{
        private const val BASE_URL = "https://timetable.spbu.ru/api/v1/"
        private const val TAG = "GroupsFragment"
    }
}
