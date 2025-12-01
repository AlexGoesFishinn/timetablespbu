package org.alexgoesfishinn.timetablespbu.data.repo


import android.util.Log
import org.alexgoesfishinn.timetablespbu.data.network.mappers.level.LevelApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.LevelsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.network.utils.WarningsNotificator
import org.alexgoesfishinn.timetablespbu.data.repo.SubscribeDivisionsRepositoryImpl.Companion
import org.alexgoesfishinn.timetablespbu.data.storage.dao.LevelDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.level.LevelDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface LevelsRepository {
    suspend fun getLevels(alias: String): List<Level>

}

class SubscribeLevelsRepositoryImpl @Inject constructor(
    private val levelsService: LevelsService,
    private val internetChecker: InternetChecker,
    private val levelApiToDbMapper: LevelApiToDbMapper,
    private val levelDbToDomainMapper: LevelDbToDomainMapper,
    private val levelDao: LevelDao,
    private val warningsNotificator: WarningsNotificator
) : LevelsRepository {


    override suspend fun getLevels(alias: String): List<Level> {
        if(internetChecker.isInternetAvailable()){
            try {
                val levelsDb = levelsService.getLevels(alias).map { levelApiToDbMapper.invoke(it) }
                levelDao.insertLevels(alias, *levelsDb.toTypedArray())
            } catch (ste: SocketTimeoutException){
                warningsNotificator.serverTimeoutNotify()
                Log.e(TAG, "ste message = ${ste.message}")
            }
            catch (ioe: IOException) {warningsNotificator.apiErrorNotify()
                Log.e(TAG, "message = ${ioe.message}")}
            catch (e: Exception){warningsNotificator.apiErrorNotify()
                Log.e(SubscribeDivisionsRepositoryImpl.TAG, "message = ${e.message}")
            }


        } else{warningsNotificator.internetIsNotAvailableNotify()}

        return levelDao.getAlias(alias).map { levelDbToDomainMapper.invoke(it) }
    }

    companion object{
        const val TAG = "SubscribeLevelsRepositoryImpl"
    }
}