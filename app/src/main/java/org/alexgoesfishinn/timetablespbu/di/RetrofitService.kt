package org.alexgoesfishinn.timetablespbu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionsService
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.data.network.services.GroupsService
import org.alexgoesfishinn.timetablespbu.data.network.services.LevelsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * @author a.bylev
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitService {

    private const val BASE_URL = "https://timetable.spbu.ru/api/v1/"



    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    }

    @Provides
    fun provideDivisionService(retrofit: Retrofit): DivisionsService{
        return retrofit.create(DivisionsService::class.java)
    }

    @Provides
    fun provideLevelsService(retrofit: Retrofit): LevelsService{
        return retrofit.create(LevelsService::class.java)
    }

    @Provides
    fun provideGroupsService(retrofit: Retrofit): GroupsService{
        return retrofit.create(GroupsService::class.java)
    }

    @Provides
    fun provideEventsService(retrofit: Retrofit): EventsService{
        return retrofit.create(EventsService::class.java)
    }


}