package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.ProgramCombinationsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import javax.inject.Inject

interface SubscribeProgramCombinationsUseCase {
    suspend fun getProgramCombinations(levelId: Long): List<ProgramCombination>
}

class SubscribeProgramCombinationsUseCaseImpl @Inject constructor(
    private val programCombinationsRepository: ProgramCombinationsRepository
): SubscribeProgramCombinationsUseCase{
    override suspend fun getProgramCombinations(levelId: Long): List<ProgramCombination> {
        return programCombinationsRepository.getProgramCombinations(levelId)
    }
}