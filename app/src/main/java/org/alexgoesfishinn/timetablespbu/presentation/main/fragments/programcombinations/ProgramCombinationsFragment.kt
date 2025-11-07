package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.programcombinations

import android.os.Bundle
import android.util.Log
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
import org.alexgoesfishinn.timetablespbu.databinding.ProgramCombinationsFragmentBinding
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
    private lateinit var programCombinationsRecycler: RecyclerView
    private lateinit var programCombinationsAdapter: ProgramCombinationsAdapter
    private lateinit var programCombinationsLabel: TextView
    private lateinit var divisionLevelName: String
    private lateinit var layout: ConstraintLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProgramCombinationsFragmentBinding.bind(view)
        programCombinationsLabel = view.findViewById(R.id.program_combinations_label)
        divisionLevelName = args.levelName
        programCombinationsLabel.text = divisionLevelName
        programCombinationsRecycler = view.findViewById(R.id.program_combinations_recycler)
        layout = view.findViewById(R.id.program_combinations_fragment_layout)
        playAnimation(layout)
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
                if(it.isNotEmpty()){
                    playAnimation(programCombinationsRecycler)
                }
                Log.i(TAG, "$it")
            }
        }
    }

    private fun playAnimation(view: View){
        val animation = AlphaAnimation(0f, 1f).apply {
            duration = 500L
            fillAfter = true
        }
        view.startAnimation(animation)
    }

    private fun navigateToPrograms(programCombinationId: Long, programName: String) {
        val label = getString(R.string.slash_string, divisionLevelName, programName)
//        findNavController().navigate(
//            ProgramCombinationsFragmentDirections.actionProgramCombinationsToPrograms(
//                programCombinationId, programName
//            )
//        )
        findNavController().navigate(
            ProgramCombinationsFragmentDirections.actionProgramCombinationsToPrograms(
                programCombinationId, label
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