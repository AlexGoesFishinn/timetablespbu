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

    @Singleton
    @Provides
    fun provideLevelDao(db: AppDatabase) = db.levelDao()

    @Singleton
    @Provides
    fun provideProgramCombinationDao(db: AppDatabase) = db.programCombinationDao()

    @Singleton
    @Provides
    fun provideProgramDao(db: AppDatabase) = db.programDao()

    @Singleton
    @Provides
    fun provideGroupDao(db: AppDatabase) = db.groupDao()

    @Singleton
    @Provides
    fun provideGroupEventsDao(db: AppDatabase) = db.groupEventsDao()

    @Singleton
    @Provides
    fun provideDayDao(db: AppDatabase) = db.dayDao()

    @Singleton
    @Provides
    fun provideEventDao(db: AppDatabase) = db.eventDao()

    @Singleton
    @Provides
    fun provideLocationDao(db: AppDatabase) = db.locationDao()

    @Singleton
    @Provides
    fun provideEducatorDao(db: AppDatabase) = db.educatorDao()

    @Singleton
    @Provides
    fun provideFavouriteDao(db: AppDatabase) = db.favouriteDao()
}