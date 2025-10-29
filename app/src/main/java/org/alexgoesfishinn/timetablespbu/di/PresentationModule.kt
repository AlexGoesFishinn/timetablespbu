package org.alexgoesfishinn.timetablespbu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.groupisnotavailable.GroupIsNotAvailableDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.groupisnotavailable.MoreDetailsDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.nointernet.NoInternetDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.servertimeout.ServerTimeoutErrorDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.somethingwentwrong.SomethingWentWrongDialog
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
//    @Singleton
    fun provideNoInternetDialog() = NoInternetDialog()

    @Provides
    @Singleton
    fun provideSomethingWentWrongDialog() = SomethingWentWrongDialog()

    @Provides
    @Singleton
    fun provideGroupIsNotAvailableDialog() = GroupIsNotAvailableDialog()

    @Provides
    @Singleton
    fun provideMoreDetailsDialog() = MoreDetailsDialog()
}