package org.alexgoesfishinn.timetablespbu.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionsService
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.data.network.services.GroupsService
import org.alexgoesfishinn.timetablespbu.data.network.services.LevelsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author a.bylev
 */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

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

    @Provides
    fun provideInternetChecker(@ApplicationContext context: Context): InternetChecker {
        return InternetChecker(context)
    }


}