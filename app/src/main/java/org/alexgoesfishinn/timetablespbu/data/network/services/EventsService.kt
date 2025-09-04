package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
/**
 * @author a.bylev
 */
interface EventsService {


    @GET("groups/{id}/events")
    suspend fun getEventsForCurrentWeek(
        @Path("id") id: String
    ): Response<GroupEvents>

    @GET("groups/{id}/events/{from}")
    suspend fun getEventsForNotCurrentWeek(
        @Path("id") id: String,
        @Path("from") from: String
    ): Response<GroupEvents>
}