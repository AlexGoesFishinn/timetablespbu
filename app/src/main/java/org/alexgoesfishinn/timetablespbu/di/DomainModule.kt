package org.alexgoesfishinn.timetablespbu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.EventsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.FavouriteRepository
import org.alexgoesfishinn.timetablespbu.data.repo.GroupEventsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.GroupsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.LevelsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.ProgramCombinationsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.ProgramsRepository
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeDivisionsUseCaseImpl
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeEventsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeEventsUseCaseImpl
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeFavouriteUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeFavouriteUseCaseImpl
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeGroupEventsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeGroupEventsUseCaseImpl
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeGroupsUseCase
import org.alexgoesfishinn.timetablespbu.domain.usecases.SubscribeGroupsUseCaseImpl
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

    @Singleton
    @Provides
    fun provideSubscribeGroupsUseCase(repository: GroupsRepository): SubscribeGroupsUseCase{
        return SubscribeGroupsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeGroupEventsUseCase(repository: GroupEventsRepository): SubscribeGroupEventsUseCase {
        return SubscribeGroupEventsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeEventsUseCase(repository: EventsRepository): SubscribeEventsUseCase{
        return SubscribeEventsUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideSubscribeFavouriteUseCase(repository: FavouriteRepository): SubscribeFavouriteUseCase {
        return SubscribeFavouriteUseCaseImpl(repository)
    }
}