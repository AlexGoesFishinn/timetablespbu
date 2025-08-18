package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.json.Json
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.databinding.ProgramCombinationsFragmentBinding
import org.alexgoesfishinn.timetablespbu.domain.entities.Program
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.ProgramCombinationsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.ProgramCombinationsClickListener


class ProgramCombinationsFragment : Fragment(R.layout.program_combinations_fragment) {
    private var binding: ProgramCombinationsFragmentBinding? = null
    private val args: ProgramCombinationsFragmentArgs by navArgs()
    private lateinit var programCombinations: List<ProgramCombination>
    private lateinit var programCombinationsRecycler: RecyclerView
    private lateinit var programCombinationsAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var programCombinationsLabel: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProgramCombinationsFragmentBinding.bind(view)
        val programCombinationsJson = args.programCombinations
        programCombinations =
            Json.decodeFromString<List<ProgramCombination>>(programCombinationsJson)
        manager = LinearLayoutManager(requireContext())
        programCombinationsLabel = view.findViewById(R.id.programCombinationsLabel)
        programCombinationsLabel.text = args.levelName
        programCombinationsRecycler = view.findViewById(R.id.programCombinationsRecycler)
        programCombinationsAdapter = ProgramCombinationsAdapter(
            programCombinations,
            object : ProgramCombinationsClickListener {
                override fun onClick(programs: List<Program>, programName: String) {
                    navigateToPrograms(programs, programName)
                }
            }
        )
        programCombinationsRecycler.apply {
            layoutManager = manager
            adapter = programCombinationsAdapter
        }


    }

    private fun navigateToPrograms(programs: List<Program>, programName: String) {
        val programsJson: String = Json.encodeToString(programs)
        findNavController().navigate(
            ProgramCombinationsFragmentDirections.actionProgramCombinationsToPrograms(
                programsJson, programName
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}