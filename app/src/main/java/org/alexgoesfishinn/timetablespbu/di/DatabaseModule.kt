package org.alexgoesfishinn.timetablespbu.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.storage.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provide(@ApplicationContext context: Context): AppDatabase =
        databaseBuilder(context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideDivisionDao(db: AppDatabase) = db.divisionDao()
}