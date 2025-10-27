package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramCombinationDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.programcombination.ProgramCombinationDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface ProgramCombinationsRepository {
    suspend fun getProgramCombinations(levelId: Long): List<ProgramCombination>
}

class SubscribeProgramCombinationsRepositoryImpl @Inject constructor(
    private val programCombinationDao: ProgramCombinationDao,
    private val programCombinationDbToDomainMapper: ProgramCombinationDbToDomainMapper
): ProgramCombinationsRepository{
    override suspend fun getProgramCombinations(levelId: Long): List<ProgramCombination> {
        return programCombinationDao.getLevelId(levelId).map { programCombinationDbToDomainMapper.invoke(it) }
    }


}