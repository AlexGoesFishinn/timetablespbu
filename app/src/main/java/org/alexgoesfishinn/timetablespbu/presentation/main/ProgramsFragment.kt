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
import kotlinx.serialization.json.Json
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.databinding.ProgramsFragmentBinding
import org.alexgoesfishinn.timetablespbu.domain.entities.Program
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.ProgramsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.ProgramsClickListener

class ProgramsFragment: Fragment(R.layout.programs_fragment) {

    private  var binding: ProgramsFragmentBinding? = null
    private val args: ProgramsFragmentArgs by navArgs()
    private lateinit var programs: List<Program>
    private lateinit var programsRecycler: RecyclerView
    private lateinit var programsAdapter: ProgramsAdapter
    private lateinit var manager: LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProgramsFragmentBinding.bind(view)
        val programsJson = args.programs
        programs = Json.decodeFromString<List<Program>>(programsJson)
        manager = LinearLayoutManager(requireContext())
        programsAdapter = ProgramsAdapter(data = programs, object: ProgramsClickListener{
            override fun onClick(programId: Long) {
                navigateToGroups(programId)
            }
        })
        programsRecycler = view.findViewById(R.id.programsRecycler)
        programsRecycler.apply {
            adapter = programsAdapter
            layoutManager = manager
        }
    }

    private fun navigateToGroups(programId: Long){
        findNavController().navigate(ProgramsFragmentDirections.actionProgramsToGroups(programId.toString()))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}