package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.data.network.entities.DivisionApi
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author a.bylev
 */

interface DivisionsService {

//    @GET("study/divisions")
//    suspend fun getDivisions(): Response<List<DivisionApi>>
    @GET("study/divisions")
    suspend fun getDivisions(): List<DivisionApi>
}