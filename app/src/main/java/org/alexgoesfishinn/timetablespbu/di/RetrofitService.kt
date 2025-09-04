package org.alexgoesfishinn.timetablespbu.di

import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionsService
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.data.network.services.GroupsService
import org.alexgoesfishinn.timetablespbu.data.network.services.LevelsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * @author a.bylev
 */
object RetrofitService {
    private lateinit var retrofit: Retrofit
    private const val BASE_URL = "https://timetable.spbu.ru/api/v1/"

    private fun provideRetrofit(): Retrofit {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        return retrofit
    }

    val divisionsService = provideRetrofit().create(DivisionsService::class.java)
    val levelsService = provideRetrofit().create(LevelsService::class.java)
    val groupsService = provideRetrofit().create(GroupsService::class.java)
    val eventsService = provideRetrofit().create(EventsService::class.java)
}