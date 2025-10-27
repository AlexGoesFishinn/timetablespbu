package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.data.network.entities.LevelApi
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author a.bylev
 */
interface LevelsService {


    @GET("study/divisions/{alias}/programs/levels")
    suspend fun getLevels(@Path("alias") alias: String): List<LevelApi>
}