package org.alexgoesfishinn.timetablespbu.presentation.main.fragments.levels

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
import org.alexgoesfishinn.timetablespbu.databinding.LevelsFragmentBinding
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.LevelsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapters.LevelsClickListener

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class LevelsFragment: Fragment(R.layout.levels_fragment) {
    private var binding: LevelsFragmentBinding? = null
    private val args: LevelsFragmentArgs by navArgs()
    private val viewmodel by viewModels<LevelsViewModel>()
    private lateinit var levelsRecycler: RecyclerView
    private lateinit var levelsAdapter: LevelsAdapter
    private lateinit var divisionName: String
    private lateinit var layout: ConstraintLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LevelsFragmentBinding.bind(view)
        levelsRecycler = view.findViewById(R.id.levels_recycler)
        val label:TextView = view.findViewById(R.id.levels_fragment_label)
//        label.text = getString(R.string.level_fragment_division_label_text, args.name)
        divisionName = args.name
        label.text = divisionName
        layout = view.findViewById(R.id.levels_fragment_layout)
        playAnimation(layout)
        initLevelsRecycler()
        subscribeToLevels()
    }

    private fun initLevelsRecycler(){
        levelsAdapter = LevelsAdapter(object : LevelsClickListener {
            override fun onClick(levelId: Long, levelName: String) {
                navigateToProgramCombinationsFragment(levelId, levelName)
            }
        })
        levelsRecycler.adapter = levelsAdapter
        levelsRecycler.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun subscribeToLevels(){
       lifecycleScope.launch {
           viewmodel.levels.collect {
                levelsAdapter.data = it
               if(it.isEmpty()){
                   playAnimation(levelsRecycler)
               }
               Log.i(TAG, it.toString())
           }

       }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun playAnimation(view: View){
        val animation = AlphaAnimation(0f, 1f).apply {
            duration = 500L
            fillAfter = true
        }
        view.startAnimation(animation)
    }

    private fun navigateToProgramCombinationsFragment(levelId: Long, levelName: String){
        val label = getString(R.string.slash_string, divisionName, levelName)
//        findNavController().navigate(LevelsFragmentDirections.actionLevelsToProgramCombinations(levelId, levelName))
        findNavController().navigate(LevelsFragmentDirections.actionLevelsToProgramCombinations(levelId, label))
   }

    private companion object{

        private const val TAG = "LevelsFragment"
    }

}