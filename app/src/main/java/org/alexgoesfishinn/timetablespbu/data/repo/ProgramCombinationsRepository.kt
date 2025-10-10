package org.alexgoesfishinn.timetablespbu.data.repo

import android.util.Log
import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramCombinationDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.programcombination.ProgramCombinationDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import javax.inject.Inject

interface ProgramCombinationsRepository {
    suspend fun getProgramCombinations(levelId: Long): List<ProgramCombination>
}

class SubscribeProgramCombinationsRepositoryImpl @Inject constructor(
    private val programCombinationDao: ProgramCombinationDao,
    private val programCombinationDbToDomainMapper: ProgramCombinationDbToDomainMapper
): ProgramCombinationsRepository{
    override suspend fun getProgramCombinations(levelId: Long): List<ProgramCombination> {
//        Log.i(TAG, "all = ${programCombinationDao.getAll()}")
//        val programCombination = mutableListOf<ProgramCombination>()
//        val programCombinationsDb = programCombinationDao.getLevelId(levelId)
//        Log.i(TAG, "programCombinationsDb = $programCombinationsDb")
//        programCombinationsDb.forEach {
//            programCombination.add(programCombinationDbToDomainMapper.invoke(it))
//        }
//        Log.i(TAG, "programCombination = $programCombination")
//        return programCombination
        return programCombinationDao.getLevelId(levelId).map { programCombinationDbToDomainMapper.invoke(it) }
    }

    private companion object{
        const val TAG = "ProgramCombinationsRepository"
    }
}