package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LevelsService {
    @GET("divisions/{alias}/programs/levels")
    fun getLevels(@Path("alias") alias: String): Call<List<Level>>
}