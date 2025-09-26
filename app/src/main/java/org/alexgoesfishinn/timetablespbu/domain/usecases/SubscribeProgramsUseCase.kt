package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.ProgramsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.Program
import javax.inject.Inject

interface SubscribeProgramsUseCase {
    suspend fun getPrograms(programCombinationId: Long): List<Program>
}

class SubscribeProgramsUseCaseImpl @Inject constructor(
    private val programsRepository: ProgramsRepository
): SubscribeProgramsUseCase{
    override suspend fun getPrograms(programCombinationId: Long): List<Program> {
        return programsRepository.getPrograms(programCombinationId)
    }
}