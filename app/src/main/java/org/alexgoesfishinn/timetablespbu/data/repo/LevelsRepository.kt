package org.alexgoesfishinn.timetablespbu.data.repo


import android.util.Log
import org.alexgoesfishinn.timetablespbu.data.network.mappers.level.LevelApiToDbMapper
//import org.alexgoesfishinn.timetablespbu.data.network.mappers.level.LevelApiToDomainMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.LevelsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.storage.dao.LevelDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramCombinationDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramDao
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.level.LevelDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import org.alexgoesfishinn.timetablespbu.domain.entities.LevelN
import javax.inject.Inject

interface LevelsRepository {
    suspend fun getLevels(alias: String): List<Level>
}

class SubscribeLevelsRepositoryImpl @Inject constructor(
    private val levelsService: LevelsService,
    private val internetChecker: InternetChecker,
//    private val levelApiToDomainMapper: LevelApiToDomainMapper
    private val levelApiToDbMapper: LevelApiToDbMapper,
    private val levelDbToDomainMapper: LevelDbToDomainMapper,
    private val levelDao: LevelDao,
    private val programCombinationDao: ProgramCombinationDao,
    private val programDao: ProgramDao
) : LevelsRepository {

    override suspend fun getLevels(alias: String): List<Level> {
        if(internetChecker.isInternetAvailable()){
            val levelsDb = mutableListOf<LevelDb>()
            levelsService.getLevels(alias).forEach {
                l -> levelsDb.add(levelApiToDbMapper.invoke(l))
            }
            levelDao.insertLevels(alias, *levelsDb.toTypedArray())
            Log.i("LevelRepository", "internetchecker = ${internetChecker.isInternetAvailable()}")
            Log.i("LevelRepository", "levelsDb = $levelsDb")
//            levelDao.getAlias(alias).forEach {
//                l -> programCombinationDao.insertAll(l.)
//            }
        }
        val levels = mutableListOf<Level>()
        levelDao.getAlias(alias).forEach { l -> levels.add(levelDbToDomainMapper.invoke(l)) }
        Log.i("LevelRepository", "levelsDb = $levels")
        return levels
    }

//    override suspend fun getLevels(alias: String): List<Level> {
//        if(internetChecker.isInternetAvailable()){
//            val levelsApi = levelsService.getLevels(alias)
//            val levels = mutableListOf<Level>()
//            levelsApi.forEach { l -> levels.add(levelApiToDomainMapper.invoke(l)) }
//            return levels
//        } else{
////          TODO("В случае отсутствия сети")
//            val levelsApi = levelsService.getLevels(alias)
//            val levels = mutableListOf<Level>()
//            levelsApi.forEach { l -> levels.add(levelApiToDomainMapper.invoke(l)) }
//            return levels
//        }
//    }

}