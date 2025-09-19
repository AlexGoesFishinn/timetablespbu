package org.alexgoesfishinn.timetablespbu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.LevelsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.SubscribeDivisionsRepositoryImpl
import org.alexgoesfishinn.timetablespbu.data.repo.SubscribeLevelsRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    abstract fun bindDivisionsRepository(impl: SubscribeDivisionsRepositoryImpl,): DivisionsRepository

    @Binds
    abstract fun bindLevelsRepository(impl: SubscribeLevelsRepositoryImpl,): LevelsRepository
}