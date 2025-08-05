package org.alexgoesfishinn.timetablespbu.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.alexgoesfishinn.timetablespbu.R

/**
 * @author a.bylev
 */
class MainActivity: AppCompatActivity() {
//    private lateinit var divisionRecycler: RecyclerView
//    private lateinit var divisionAdapter: RecyclerView.Adapter<*>
//    private lateinit var manager: RecyclerView.LayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //manager =


//        getDivisions()
    }

//    private fun getDivisions(){
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val service = retrofit.create(DivisionService::class.java)
//        service.getDivisions().enqueue(object : Callback<List<Division>> {
//            override fun onResponse(call: Call<List<Division>>, response: Response<List<Division>>) {
//                if(response.isSuccessful){
//                    val data = response.body()
//                    Log.e(TAG, data.toString())
//                    divisionRecycler = findViewById<RecyclerView?>(R.id.divisionRecycler).apply {
//                        divisionAdapter = DivisionAdapter(data!!)
//                        adapter = divisionAdapter
//                        layoutManager = LinearLayoutManager(this@MainActivity)
//                    }
//
//
//
//                }
//            }
//
//            override fun onFailure(call: Call<List<Division>>, t: Throwable) {
//                Log.e(TAG, "Сетевая ошибка ${t.message}")
//            }
//
//        })
//
//    }

//    private companion object{
//        private const val BASE_URL = "https://timetable.spbu.ru/api/v1/study/"
//        private const val TAG = "MainActivity"
//    }
}