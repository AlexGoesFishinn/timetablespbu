package org.alexgoesfishinn.timetablespbu.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.domain.DataStoreManager

/**
 * @author a.bylev
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreManager {
    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager = DataStoreManager(context)
}