package org.alexgoesfishinn.timetablespbu.presentation.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.databinding.DivisionsFragmentBinding
import org.alexgoesfishinn.timetablespbu.di.RetrofitService
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DivisionsAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DivisionsClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DivisionsFragment : Fragment(R.layout.divisions_fragment) {
    private var binding: DivisionsFragmentBinding? = null

    private lateinit var divisionRecycler: RecyclerView
    private lateinit var divisionAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager
    private var divisions: List<Division> = emptyList()

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
            showContent()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showContent(){
        val internetChecker = InternetChecker()
        if(internetChecker.isInternetAvailable(requireContext())){
            getDivisions()
        } else {
            Log.e(TAG, "no internet available")
            showNoInternetDialog()
        }
    }

    private fun showNoInternetDialog(){
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Нет подключения к интернету")
            .setMessage("Проверьте подключение к сети")
            .setPositiveButton("ОК"
            ) { dialog, which -> showContent() }
        val noInternetDialog = builder.create()
        noInternetDialog.show()
    }

    private fun navigateToLevelsFragment(alias: String, name: String) {
        findNavController().navigate(DivisionsFragmentDirections.actionDivisionsToLevels(
            alias = alias,
            name = name
        ))
    }

    private fun getDivisions() {

        val service = RetrofitService.divisionsService
        lifecycleScope.launch {
            val response = service.getDivisions()
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


