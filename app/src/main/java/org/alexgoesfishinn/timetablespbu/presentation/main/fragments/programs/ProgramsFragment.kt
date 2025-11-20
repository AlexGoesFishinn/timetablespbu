package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.programs

import android.os.Bundle
import android.util.Log
import android.view.View
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
import org.alexgoesfishinn.timetablespbu.databinding.ProgramsFragmentBinding
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.ProgramsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.ProgramsClickListener
import org.alexgoesfishinn.timetablespbu.presentation.main.utils.Animations
import javax.inject.Inject

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class ProgramsFragment: Fragment(R.layout.programs_fragment) {

    private  var binding: ProgramsFragmentBinding? = null
    private val args: ProgramsFragmentArgs by navArgs()
    private val viewmodel by viewModels<ProgramsViewModel>()
    private lateinit var programsRecycler: RecyclerView
    private lateinit var programsAdapter: ProgramsAdapter
    private lateinit var programLabel: TextView
    private lateinit var programName: String
    private lateinit var layout: ConstraintLayout
    @Inject
    lateinit var animations: Animations

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProgramsFragmentBinding.bind(view)
        programName = args.programName
        programLabel = view.findViewById(R.id.program_label)
        programLabel.text = programName
        programsRecycler = view.findViewById(R.id.programs_recycler)
        layout = view.findViewById(R.id.programs_fragment_layout)
        animations.fadeIn(layout)
//        playAnimation(layout)
        initProgramsRecycler()
        subscribeToPrograms()
    }

    private fun initProgramsRecycler(){
        programsAdapter = ProgramsAdapter(object : ProgramsClickListener {
            override fun onClick(programId: Long, programYear: String) {
                navigateToGroups(programId, programYear)
            }
        }
        )
        programsRecycler.adapter = programsAdapter
        programsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToPrograms(){
        lifecycleScope.launch {
            viewmodel.programs.collect{
                programsAdapter.data = it
                if(it.isNotEmpty()){
                    animations.fadeIn(programsRecycler)
//                    playAnimation(programsRecycler)
                }
                Log.i(TAG, "$it")
            }
        }
    }

//    private fun playAnimation(view: View){
//        val animation = AlphaAnimation(0f, 1f).apply {
//            duration = 500L
//            fillAfter = true
//        }
//        view.startAnimation(animation)
//    }

    private fun navigateToGroups(programId: Long, programYear: String){
        findNavController().navigate(ProgramsFragmentDirections.actionProgramsToGroups(programId, programName, programYear))
    }
    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private companion object{
        const val TAG = "ProgramsFragment"
    }
}