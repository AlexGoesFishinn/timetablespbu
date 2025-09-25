package org.alexgoesfishinn.timetablespbu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.LevelsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.ProgramCombinationsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.ProgramsRepository
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCaseImpl
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeLevelsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeLevelsUseCaseImpl
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeProgramCombinationsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeProgramCombinationsUseCaseImpl
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeProgramsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeProgramsUseCaseImpl
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

    @Singleton
    @Provides
    fun provideSubscribeProgramCombinationsUseCase(repository: ProgramCombinationsRepository): SubscribeProgramCombinationsUseCase{
        return SubscribeProgramCombinationsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeProgramsUseCase(repository: ProgramsRepository): SubscribeProgramsUseCase{
        return SubscribeProgramsUseCaseImpl(repository)
    }
}