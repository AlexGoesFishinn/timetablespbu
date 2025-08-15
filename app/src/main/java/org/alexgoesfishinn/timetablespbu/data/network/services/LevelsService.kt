package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LevelsService {
//    @GET("study/divisions/{alias}/programs/levels")
//    fun getLevels(@Path("alias") alias: String): Call<List<Level>>

    @GET("study/divisions/{alias}/programs/levels")
    suspend fun getLevels(@Path("alias") alias: String): Response<List<Level>>
}