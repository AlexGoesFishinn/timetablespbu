package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.groups

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.databinding.GroupsFragmentBinding
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.GroupsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.GroupsClickListener

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class GroupsFragment : Fragment(R.layout.groups_fragment) {
    private var binding: GroupsFragmentBinding? = null
    private val args: GroupsFragmentArgs by navArgs()
    private val viewmodel by viewModels<GroupsViewModel>()
    private lateinit var groupsRecycler: RecyclerView
    private lateinit var groupsAdapter: GroupsAdapter
    private lateinit var programGroupLabel: TextView
    private lateinit var yearGroupLabel: TextView
    private lateinit var layout: ConstraintLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GroupsFragmentBinding.bind(view)
        val programName: String = args.programName
        val programYear: String = args.programYear
        programGroupLabel = view.findViewById(R.id.program_group_label)
        yearGroupLabel = view.findViewById(R.id.groups_label)
//        programGroupLabel.text = getString(R.string.groups_fragment_program_label, programName)
//        yearGroupLabel.text = getString(R.string.groups_fragment_year_label, programYear)
        programGroupLabel.text = getString(R.string.slash_string, programName, programYear)
        groupsRecycler = view.findViewById(R.id.groups_recycler)
        layout = view.findViewById(R.id.groups_fragment_layout)
        playAnimation(layout)
        initRecycler()
        subscribeToGroups()


    }

    private fun subscribeToGroups() {
        lifecycleScope.launch {
            viewmodel.groups.collect {
//                groups ->
//                groupsAdapter.data = groups.sortedBy { it.groupName }
//                if (groups.isNotEmpty()) {
//                    playAnimation(groupsRecycler)
//                }
                groupsAdapter.data = it
                if (it.isNotEmpty()) {
                    playAnimation(groupsRecycler)
                }

            }
        }

    }

    private fun playAnimation(view: View) {
        val animation = AlphaAnimation(0f, 1f).apply {
            duration = 500L
            fillAfter = true
        }
        view.startAnimation(animation)
    }


    private fun initRecycler() {
        groupsAdapter = GroupsAdapter(
            object : GroupsClickListener {
                override fun onClick(groupId: Long) {
                    navigateToEvents(groupId)
                }
            }
        )
        groupsRecycler.adapter = groupsAdapter
        groupsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun navigateToEvents(groupId: Long) {
        findNavController().navigate(GroupsFragmentDirections.actionGroupToEvents(groupId))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }


}