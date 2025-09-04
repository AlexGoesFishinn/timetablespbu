package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.domain.entities.Level

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
/**
 * @author a.bylev
 */
interface LevelsService {


    @GET("study/divisions/{alias}/programs/levels")
    suspend fun getLevels(@Path("alias") alias: String): Response<List<Level>>
}