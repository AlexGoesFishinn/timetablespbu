package org.alexgoesfishinn.timetablespbu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.SubscribeDivisionsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    abstract fun bindDivisionsRepository(impl: SubscribeDivisionsRepositoryImpl,): DivisionsRepository
}