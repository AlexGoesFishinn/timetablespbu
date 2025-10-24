package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.data.network.entities.GroupEventsApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
/**
 * @author a.bylev
 */
interface EventsService {


    @GET("groups/{id}/events")
    suspend fun checkGroup(
        @Path("id") id: Long
    ): Response<GroupEventsApi>

    @GET("groups/{id}/events/{from}")
    suspend fun getEvents(
        @Path("id") id: Long,
        @Path("from") from: String
    ): GroupEventsApi
}