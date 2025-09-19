package org.alexgoesfishinn.timetablespbu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.LevelsRepository
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCaseImpl
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeLevelsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeLevelsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideSubscribeDivisionsUseCase(repository: DivisionsRepository): SubscribeDivisionsUseCase {
        return SubscribeDivisionsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeLevelsUseCase(repository: LevelsRepository): SubscribeLevelsUseCase{
        return SubscribeLevelsUseCaseImpl(repository)
    }
}