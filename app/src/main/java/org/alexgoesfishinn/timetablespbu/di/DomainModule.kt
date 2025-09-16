package org.alexgoesfishinn.timetablespbu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideSubscribeDivisionsUseCase(repository: DivisionsRepository): SubscribeDivisionsUseCase {
        return SubscribeDivisionsUseCaseImpl(repository)
    }
}