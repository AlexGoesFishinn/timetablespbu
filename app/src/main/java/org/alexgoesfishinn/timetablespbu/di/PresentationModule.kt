package org.alexgoesfishinn.timetablespbu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.GroupIsNotAvailableDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.NoInternetDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.ServerTimeoutErrorDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.SomethingWentWrongDialog
import javax.inject.Singleton
/**
 * @author a.bylev
 */
@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun provideServerTimeoutErrorDialog() = ServerTimeoutErrorDialog()

    @Provides
    @Singleton
    fun provideNoInternetDialog() = NoInternetDialog()

    @Provides
    @Singleton
    fun provideSomethingWentWrongDialog() = SomethingWentWrongDialog()

    @Provides
    @Singleton
    fun provideGroupIsNotAvailableDialog() = GroupIsNotAvailableDialog()
}