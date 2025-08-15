package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramGroups
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupsService {
//    @GET("programs/{id}/groups")
//    fun getGroups(@Path("id") id: String): Call<ProgramGroups>
      @GET("programs/{id}/groups")
      suspend fun getGroups(@Path("id") id: String): Response<ProgramGroups>
}