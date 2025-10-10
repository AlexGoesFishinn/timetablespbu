package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.program.ProgramDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Program
import javax.inject.Inject

interface ProgramsRepository {
    suspend fun getPrograms(programCombinationId: Long): List<Program>
}

class SubscribeProgramsRepositoryImpl @Inject constructor(
    private val programDao: ProgramDao,
    private val programDbToDomainMapper: ProgramDbToDomainMapper
): ProgramsRepository{
    override suspend fun getPrograms(programCombinationId: Long): List<Program> {
//        val programs = mutableListOf<Program>()
//        programDao.getProgramCombinationId(programCombinationId).forEach {
//            p -> programs.add(programDbToDomainMapper.invoke(p))
//        }
//        return programs
        return programDao.getProgramCombinationId(programCombinationId).map { programDbToDomainMapper.invoke(it) }
    }
}