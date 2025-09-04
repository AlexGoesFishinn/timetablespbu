package org.alexgoesfishinn.timetablespbu.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker

/**
 * @author a.bylev
 */
@Module
@InstallIn(SingletonComponent::class)
object InternetChecker {

    @Provides
    fun provideInternetChecker(@ApplicationContext context: Context): InternetChecker{
        return InternetChecker(context)
    }
}