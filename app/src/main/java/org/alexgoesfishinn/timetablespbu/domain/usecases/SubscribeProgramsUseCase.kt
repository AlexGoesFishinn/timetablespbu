package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.ProgramsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramN
import javax.inject.Inject

interface SubscribeProgramsUseCase {
    suspend fun getPrograms(programCombinationId: Long): List<ProgramN>
}

class SubscribeProgramsUseCaseImpl @Inject constructor(
    private val programsRepository: ProgramsRepository
): SubscribeProgramsUseCase{
    override suspend fun getPrograms(programCombinationId: Long): List<ProgramN> {
        return programsRepository.getPrograms(programCombinationId)
    }
}