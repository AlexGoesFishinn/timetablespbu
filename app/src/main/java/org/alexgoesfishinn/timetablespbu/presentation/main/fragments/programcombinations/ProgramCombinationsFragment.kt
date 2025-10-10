package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.programcombinations

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
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
import org.alexgoesfishinn.timetablespbu.databinding.ProgramCombinationsFragmentBinding
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.ProgramCombinationsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.ProgramCombinationsClickListener

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class ProgramCombinationsFragment : Fragment(R.layout.program_combinations_fragment) {
    private var binding: ProgramCombinationsFragmentBinding? = null
    private val viewmodel by viewModels<ProgramCombinationsViewModel>()
    private val args: ProgramCombinationsFragmentArgs by navArgs()
    private lateinit var programCombinations: List<ProgramCombination>
    private lateinit var programCombinationsRecycler: RecyclerView
    private lateinit var programCombinationsAdapter: ProgramCombinationsAdapter
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var programCombinationsLabel: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProgramCombinationsFragmentBinding.bind(view)
//        val programCombinationsJson = args.programCombinations
//        programCombinations =
//            Json.decodeFromString<List<ProgramCombination>>(programCombinationsJson)
//        manager = LinearLayoutManager(requireContext())
        programCombinationsLabel = view.findViewById(R.id.programCombinationsLabel)
        programCombinationsLabel.text = args.levelName
        programCombinationsRecycler = view.findViewById(R.id.programCombinationsRecycler)
//        programCombinationsAdapter = ProgramCombinationsAdapter(
//            programCombinations,
//            object : ProgramCombinationsClickListener {
//                override fun onClick(programs: List<Program>, programName: String) {
//                    navigateToPrograms(programs, programName)
//                }
//            }
//        )
//        programCombinationsRecycler.apply {
//            layoutManager = manager
//            adapter = programCombinationsAdapter
//        }
        initProgramCombinationsRecycler()
        subscribeToProgramCombinations()


    }

    private fun initProgramCombinationsRecycler(){
        programCombinationsAdapter = ProgramCombinationsAdapter(
            object : ProgramCombinationsClickListener {
                override fun onClick(programCombinationId: Long, programName: String) {
                    navigateToPrograms(programCombinationId, programName)
                }
            }
        )
        programCombinationsRecycler.adapter = programCombinationsAdapter
        programCombinationsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToProgramCombinations(){
        lifecycleScope.launch {
            viewmodel.programCombinations.collect {
                programCombinationsAdapter.data = it
                Log.i(TAG, "$it")
            }
        }
//        programCombinationsAdapter.data = viewmodel.programCombinations
    }

    private fun navigateToPrograms(programCombinationId: Long, programName: String) {
//        val programsJson: String = Json.encodeToString(programs)
        findNavController().navigate(
            ProgramCombinationsFragmentDirections.actionProgramCombinationsToPrograms(
                programCombinationId, programName
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private companion object{
        const val TAG = "ProgramCombinationsFragment"
    }
}