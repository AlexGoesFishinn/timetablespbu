package org.alexgoesfishinn.timetablespbu.data.repo


import org.alexgoesfishinn.timetablespbu.data.network.mappers.level.LevelApiToDomainMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.LevelsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import javax.inject.Inject

interface LevelsRepository {
    suspend fun getLevels(alias: String): List<Level>
}

class SubscribeLevelsRepositoryImpl @Inject constructor(
    private val levelsService: LevelsService,
    private val internetChecker: InternetChecker,
    private val levelApiToDomainMapper: LevelApiToDomainMapper
) : LevelsRepository {
    override suspend fun getLevels(alias: String): List<Level> {
        if(internetChecker.isInternetAvailable()){
            val levelsApi = levelsService.getLevels(alias)
            val levels = mutableListOf<Level>()
            levelsApi.forEach { l -> levels.add(levelApiToDomainMapper.invoke(l)) }
            return levels
        } else{
//          TODO("В случае отсутствия сети")
            val levelsApi = levelsService.getLevels(alias)
            val levels = mutableListOf<Level>()
            levelsApi.forEach { l -> levels.add(levelApiToDomainMapper.invoke(l)) }
            return levels
        }
    }

}