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
import org.alexgoesfishinn.timetablespbu.data.network.services.LevelsService
import org.alexgoesfishinn.timetablespbu.databinding.LevelsFragmentBinding
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import org.alexgoesfishinn.timetablespbu.domain.entities.Program
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombinations


import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.LevelsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.LevelsClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LevelsFragment: Fragment(R.layout.levels_fragment) {
    private var binding: LevelsFragmentBinding? = null
    private val args: LevelsFragmentArgs by navArgs()
    private lateinit var levelsRecycler: RecyclerView
    private lateinit var levelsAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LevelsFragmentBinding.bind(view)
        levelsRecycler = view.findViewById(R.id.levelsRecycler)
        manager = LinearLayoutManager(requireContext())

        getLevels(args.alias)
//        binding?.levels!!.text = args.alias

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun navigateToProgramCombinationsFragment(programCombinations: List<ProgramCombination>){
        val programCombinationsJson = Json.encodeToString(programCombinations)
        findNavController().navigate(LevelsFragmentDirections.actionLevelsToProgramCombinations(programCombinationsJson))


    }
    private fun getLevels(alias: String){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LevelsService::class.java)
        service.getLevels(alias).enqueue(object : Callback<List<Level>> {
            override fun onResponse(call: Call<List<Level>>, response: Response<List<Level>>) {
                if(response.isSuccessful){
                    val data = response.body()
                    Log.i(TAG, data.toString())
                    levelsRecycler.apply {
                        levelsAdapter = LevelsAdapter(data!!, object: LevelsClickListener{
                            override fun onClick(programCombinations: List<ProgramCombination>) {

                                navigateToProgramCombinationsFragment(programCombinations)
                            }
                        })
                        adapter = levelsAdapter
                        layoutManager = manager
                    }

                }
            }

            override fun onFailure(call: Call<List<Level>>, t: Throwable) {
                Log.e(TAG, "Сетевая ошибка ${t.message}")
            }

        })
    }
    private companion object{
        private const val BASE_URL = "https://timetable.spbu.ru/api/v1/study/"
        private const val TAG = "LevelsFragment"
    }

}