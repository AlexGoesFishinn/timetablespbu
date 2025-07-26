package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import org.alexgoesfishinn.timetablespbu.domain.entities.Divisions
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author a.bylev
 */

interface DivisionService {
    @GET("divisions")
    fun getDivisions(): Call<List<Division>>
}