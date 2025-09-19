package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.data.network.entities.DivisionApi
import retrofit2.http.GET

/**
 * @author a.bylev
 */

interface DivisionsService {

    @GET("study/divisions")
    suspend fun getDivisions(): List<DivisionApi>
}