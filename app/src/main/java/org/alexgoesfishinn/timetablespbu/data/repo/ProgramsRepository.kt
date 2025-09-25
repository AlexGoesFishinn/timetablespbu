package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.program.ProgramDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramN
import javax.inject.Inject

interface ProgramsRepository {
    suspend fun getPrograms(programCombinationId: Long): List<ProgramN>
}

class SubscribeProgramsRepositoryImpl @Inject constructor(
    private val programDao: ProgramDao,
    private val programDbToDomainMapper: ProgramDbToDomainMapper
): ProgramsRepository{
    override suspend fun getPrograms(programCombinationId: Long): List<ProgramN> {
        val programs = mutableListOf<ProgramN>()
        programDao.getProgramCombinationId(programCombinationId).forEach {
            p -> programs.add(programDbToDomainMapper.invoke(p))
        }
        return programs
    }
}