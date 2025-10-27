package org.alexgoesfishinn.timetablespbu.data.repo

import android.util.Log
import org.alexgoesfishinn.timetablespbu.data.network.mappers.division.DivisionApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.network.utils.WarningsNotificator
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DivisionDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.division.DivisionDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface DivisionsRepository {
    suspend fun getDivisions(): List<Division>
}

class SubscribeDivisionsRepositoryImpl @Inject constructor(
    private val divisionsService: DivisionsService,
    private val divisionDao: DivisionDao,
    private val internetChecker: InternetChecker,
    private val divisionApiToDbMapper: DivisionApiToDbMapper,
    private val divisionDbToDomainMapper: DivisionDbToDomainMapper,
    private val warningsNotificator: WarningsNotificator

) : DivisionsRepository {
    override suspend fun getDivisions(): List<Division> {
        if (internetChecker.isInternetAvailable()) {
                try {
                    val divisionsDb = divisionsService.getDivisions().map {
                        divisionApiToDbMapper.invoke(it)
                    }
                    divisionDao.insertAll(
                        *divisionsDb.toTypedArray()
                    )
                } catch (ste: SocketTimeoutException){
                    warningsNotificator.serverTimeoutNotify()
                    Log.e(TAG, "ste message = ${ste.message}")
                }
                catch (ioe: IOException) {warningsNotificator.apiErrorNotify()
                    Log.e(TAG, "message = ${ioe.message}")}



        } else{warningsNotificator.internetIsNotAvailableNotify()}

        return divisionDao.getAll().map { divisionDbToDomainMapper.invoke(it) }
    }
companion object{
    const val TAG = "SubscribeDivisionsRepositoryImpl"
}
}