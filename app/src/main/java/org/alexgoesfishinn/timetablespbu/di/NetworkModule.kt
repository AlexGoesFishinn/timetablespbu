package org.alexgoesfishinn.timetablespbu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.GroupsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.GroupsRepositoryImpl
import org.alexgoesfishinn.timetablespbu.data.repo.LevelsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.ProgramCombinationsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.ProgramsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.SubscribeDivisionsRepositoryImpl
import org.alexgoesfishinn.timetablespbu.data.repo.SubscribeLevelsRepositoryImpl
import org.alexgoesfishinn.timetablespbu.data.repo.SubscribeProgramCombinationsRepositoryImpl
import org.alexgoesfishinn.timetablespbu.data.repo.SubscribeProgramsRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    @Binds
    abstract fun bindDivisionsRepository(impl: SubscribeDivisionsRepositoryImpl,): DivisionsRepository

    @Binds
    abstract fun bindLevelsRepository(impl: SubscribeLevelsRepositoryImpl,): LevelsRepository

    @Binds
    abstract fun bindProgramCombinationsRepository(impl: SubscribeProgramCombinationsRepositoryImpl): ProgramCombinationsRepository

    @Binds
    abstract fun bindProgramRepository(impl: SubscribeProgramsRepositoryImpl): ProgramsRepository

    @Binds
    abstract fun bindGroupRepository(impl: GroupsRepositoryImpl): GroupsRepository
}