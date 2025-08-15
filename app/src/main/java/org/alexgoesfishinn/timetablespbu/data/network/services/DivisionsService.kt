package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.domain.entities.Division

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author a.bylev
 */

interface DivisionsService {
//    @GET("study/divisions")
//    fun getDivisions(): Call<List<Division>>

    @GET("study/divisions")
    suspend fun getDivisions(): Response<List<Division>>
}