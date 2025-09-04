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
import kotlinx.serialization.json.Json
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.databinding.LevelsFragmentBinding
import org.alexgoesfishinn.timetablespbu.di.RetrofitService
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.LevelsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.LevelsClickListener
import javax.inject.Inject

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class LevelsFragment: Fragment(R.layout.levels_fragment) {
    private var binding: LevelsFragmentBinding? = null
    private val args: LevelsFragmentArgs by navArgs()
    private lateinit var levelsRecycler: RecyclerView
    private lateinit var levelsAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager
    private var levels: List<Level> = emptyList()
    @Inject lateinit var internetChecker: InternetChecker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LevelsFragmentBinding.bind(view)
        levelsRecycler = view.findViewById(R.id.levelsRecycler)
        val label:TextView = view.findViewById(R.id.levelsFragmentLabel)
        label.text = "Направление " + args.name
        manager = LinearLayoutManager(requireContext())
        levelsRecycler.apply {
            layoutManager = manager
            adapter = LevelsAdapter(levels,object : LevelsClickListener{
                override fun onClick(programCombinations: List<ProgramCombination>, levelName: String) {
                    navigateToProgramCombinationsFragment(programCombinations, levelName)
                }
            })
        }
        if(levels.isEmpty()){
            getData()

        }
    }

    private fun getData(){
        if(internetChecker.isInternetAvailable()){
            getLevels(args.alias)
        } else {
            Log.e(TAG, "no internet available")
            internetChecker.showNoInternetDialog(requireContext(), { getData() })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun navigateToProgramCombinationsFragment(programCombinations: List<ProgramCombination>, levelName: String){
        val programCombinationsJson = Json.encodeToString(programCombinations)
        findNavController().navigate(LevelsFragmentDirections.actionLevelsToProgramCombinations(programCombinationsJson, levelName))
   }
    private fun getLevels(alias: String){
        val service = RetrofitService.levelsService
        lifecycleScope.launch {
            val response = service.getLevels(alias)
            Log.i(TAG, response.toString())
            if(response.isSuccessful){
                val data = response.body()
                if(data != null){
                    levels = data
                    levelsAdapter = LevelsAdapter(levels,object : LevelsClickListener {
                        override fun onClick(programCombinations: List<ProgramCombination>, levelName: String) {
                            navigateToProgramCombinationsFragment(programCombinations, levelName)
                        }
                    })
                    levelsRecycler.apply {
                        adapter = levelsAdapter
                    }
                } else Log.e(TAG, "received data is null")
            }
            else Log.e(TAG, "response if not successful")
        }

    }
    private companion object{

        private const val TAG = "LevelsFragment"
    }

}