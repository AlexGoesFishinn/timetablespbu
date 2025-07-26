package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.alexgoesfishinn.timetablespbu.R
import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionService
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import org.alexgoesfishinn.timetablespbu.domain.entities.Divisions
import org.alexgoesfishinn.timetablespbu.presentation.main.adapter.DivisionAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author a.bylev
 */
class MainActivity: AppCompatActivity() {
    private lateinit var divisionRecycler: RecyclerView
    private lateinit var divisionAdapter: RecyclerView.Adapter<*>
    private lateinit var manager: RecyclerView.LayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //manager =


        getDivisions()
    }

    private fun getDivisions(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(DivisionService::class.java)
        service.getDivisions().enqueue(object : Callback<List<Division>> {
            override fun onResponse(call: Call<List<Division>>, response: Response<List<Division>>) {
                if(response.isSuccessful){
                    val data = response.body()
                    Log.e(TAG, data.toString())
                    divisionRecycler = findViewById<RecyclerView?>(R.id.divisionRecycler).apply {
                        divisionAdapter = DivisionAdapter(data!!)
                        adapter = divisionAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }



                }
            }

            override fun onFailure(call: Call<List<Division>>, t: Throwable) {
                Log.e(TAG, "Сетевая ошибка ${t.message}")
            }

            /*override fun onResponse(
                call: Call<List<Division>>,
                response: Response<List<Division>>
            ) {
                if(response.isSuccessful){
                    val data = response.body()
                    Log.e(TAG, data.toString())

                }
            }

            override fun onFailure(call: Call<List<Division>>, t: Throwable) {
                Log.e(TAG, "Сетевая ошибка ${t.message}")
            }*/

        })

    }

    private companion object{
        private const val BASE_URL = "https://timetable.spbu.ru/api/v1/study/"
        private const val TAG = "MainActivity"
    }
}