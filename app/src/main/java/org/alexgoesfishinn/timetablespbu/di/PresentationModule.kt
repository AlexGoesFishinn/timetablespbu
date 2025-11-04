package org.alexgoesfishinn.timetablespbu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.clearcache.ClearCacheDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.groupisnotavailable.GroupIsNotAvailableDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.groupisnotavailable.MoreDetailsDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.nointernet.NoInternetDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.servertimeout.ServerTimeoutErrorDialog
import org.alexgoesfishinn.timetablespbu.presentation.main.dialogs.somethingwentwrong.SomethingWentWrongDialog
/**
 * @author a.bylev
 */
@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideServerTimeoutErrorDialog() = ServerTimeoutErrorDialog()

    @Provides
    fun provideNoInternetDialog() = NoInternetDialog()

    @Provides
    fun provideSomethingWentWrongDialog() = SomethingWentWrongDialog()

    @Provides

    fun provideGroupIsNotAvailableDialog() = GroupIsNotAvailableDialog()

    @Provides
    fun provideMoreDetailsDialog() = MoreDetailsDialog()

    @Provides
    fun provideClearCacheDialog() = ClearCacheDialog()
}