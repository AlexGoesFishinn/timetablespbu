package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.databinding.DivisionsFragmentBinding
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DivisionsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DivisionsClickListener
import javax.inject.Inject

/**
 * @author a.bylev
 */
@AndroidEntryPoint
class DivisionsFragment : Fragment(R.layout.divisions_fragment) {
    private var binding: DivisionsFragmentBinding? = null

    private lateinit var divisionRecycler: RecyclerView
    private lateinit var divisionAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager
    private var divisions: List<Division> = emptyList()
    @Inject lateinit var internetChecker: InternetChecker
    @Inject lateinit var divisionsService: DivisionsService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DivisionsFragmentBinding.bind(view)
        manager = LinearLayoutManager(requireContext())
        divisionRecycler = view.findViewById(R.id.divisionRecycler)
        divisionRecycler.apply {
            adapter = DivisionsAdapter(divisions, object : DivisionsClickListener {
                override fun onItemClick(alias: String, name: String) {
                    navigateToLevelsFragment(alias, name)
                }
            })
            layoutManager = manager
        }
        if (divisions.isEmpty()) {
            getData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun getData(){
        if(internetChecker.isInternetAvailable()){
            getDivisions()
        } else {
            Log.e(TAG, "no internet available")
            internetChecker.showNoInternetDialog(requireContext(), { getData() })

        }
    }


    private fun navigateToLevelsFragment(alias: String, name: String) {
        findNavController().navigate(DivisionsFragmentDirections.actionDivisionsToLevels(
            alias = alias,
            name = name
        ))
    }

    private fun getDivisions() {
        lifecycleScope.launch {
            val response = divisionsService.getDivisions()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    divisions = data
                    divisionAdapter = DivisionsAdapter(divisions, object : DivisionsClickListener {
                        override fun onItemClick(alias: String, name: String) {
                            navigateToLevelsFragment(alias, name)
                        }
                    })
                    divisionRecycler.apply {
                        adapter = divisionAdapter
                    }

                } else Log.i(TAG, "received data is null")
            } else {
                Log.e(TAG, "Division response is not successful")
            }
        }


    }

    private companion object {
        private const val TAG = "DivisionsFragment"
    }
}


