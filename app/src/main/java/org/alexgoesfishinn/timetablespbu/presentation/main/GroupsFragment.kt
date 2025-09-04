package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.databinding.GroupsFragmentBinding
import org.alexgoesfishinn.timetablespbu.di.RetrofitService
import org.alexgoesfishinn.timetablespbu.domain.entities.Group
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.GroupsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.GroupsClickListener
import javax.inject.Inject

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class GroupsFragment: Fragment(R.layout.groups_fragment) {
    private var binding: GroupsFragmentBinding? = null
    private val args: GroupsFragmentArgs by navArgs()
    private lateinit var groupsRecycler: RecyclerView
    private lateinit var groupsAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager
    private var groups: List<Group> = emptyList()
    private lateinit var programGroupLabel: TextView
    private lateinit var yearGroupLabel: TextView
    @Inject
    lateinit var internetChecker: InternetChecker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GroupsFragmentBinding.bind(view)
//        val programId: String = args.programId
        val programName: String = args.programName
        val programYear: String = args.programYear
        programGroupLabel = view.findViewById(R.id.programGroupLabel)
        yearGroupLabel = view.findViewById(R.id.yearGroupLabel)
        programGroupLabel.text = "Образовательная программа " + programName
        yearGroupLabel.text = programYear + " года поступления"
        manager = LinearLayoutManager(requireContext())
        groupsRecycler = view.findViewById(R.id.groupsRecycler)
        groupsRecycler.apply {
            layoutManager = manager
            adapter = GroupsAdapter(groups,object : GroupsClickListener{
                override fun onClick(groupId: Long, groupName: String) {
                    navigateToEvents(groupId, groupName)
                }
            })
        }
        if(groups.isEmpty()){
            getData()
//            getGroups(programId = programId)
        }

    }

    private fun getData(){
        if(internetChecker.isInternetAvailable()){

            getGroups()
        } else {
            Log.e(TAG, "no internet available")
            internetChecker.showNoInternetDialog(requireContext(), { getData() })
        }
    }

    private fun navigateToEvents(groupId: Long, groupName: String){
        val groupIdString = groupId.toString()
        findNavController().navigate(GroupsFragmentDirections.actionGroupToEvents(groupIdString, groupName))
    }

    private fun getGroups(){
        val programId: String = args.programId
        val service = RetrofitService.groupsService
        lifecycleScope.launch {
            val response = service.getGroups(programId)
            if(response.isSuccessful){
                val data = response.body()
                if(data != null){
                    groups = data.groups
                    groupsAdapter = GroupsAdapter(groups, object: GroupsClickListener {
                        override fun onClick(groupId: Long, groupName: String) {
                            navigateToEvents(groupId, groupName)
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
