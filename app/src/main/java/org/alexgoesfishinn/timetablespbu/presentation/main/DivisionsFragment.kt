package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionsService
import org.alexgoesfishinn.timetablespbu.databinding.DivisionsFragmentBinding
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DivisionAdapter
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DivisionClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DivisionsFragment: Fragment(R.layout.divisions_fragment) {
    private var binding: DivisionsFragmentBinding? = null

    private lateinit var divisionRecycler: RecyclerView
    private lateinit var divisionAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DivisionsFragmentBinding.bind(view)
        manager = LinearLayoutManager(requireContext())
        divisionRecycler = view.findViewById<RecyclerView>(R.id.divisionRecycler)
        getDivisions()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun navigateToLevelsFragment(alias: String){
        findNavController().navigate(DivisionsFragmentDirections.actionDivisionsToLevels(alias))
    }

    private fun getDivisions(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(DivisionsService::class.java)
        service.getDivisions().enqueue(object : Callback<List<Division>> {
            override fun onResponse(call: Call<List<Division>>, response: Response<List<Division>>) {
                if(response.isSuccessful){
                    val data = response.body()
                    Log.i(TAG, data.toString())
                    divisionRecycler.apply {
                        divisionAdapter = DivisionAdapter(data!!, object: DivisionClickListener{
                            override fun onItemClick(alias: String) {
                                navigateToLevelsFragment(alias)
                            }
                        })
                        adapter = divisionAdapter
                        layoutManager = manager
                    }



                }
            }

            override fun onFailure(call: Call<List<Division>>, t: Throwable) {
                Log.e(TAG, "Сетевая ошибка ${t.message}")
            }

        })

    }
    private companion object{
        private const val BASE_URL = "https://timetable.spbu.ru/api/v1/"
        private const val TAG = "DivisionsFragment"
    }
}


