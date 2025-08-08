package org.alexgoesfishinn.timetablespbu.data.network.services

import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsService {
    @GET("groups/{id}/events")
    fun getEventsForCurrentWeek(@Path("id") id: String): Call<GroupEvents>
}