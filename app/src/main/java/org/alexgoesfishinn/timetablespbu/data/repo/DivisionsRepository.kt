package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.data.network.mappers.division.DivisionApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DivisionDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.division.DivisionDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import javax.inject.Inject

interface DivisionsRepository {
    suspend fun getDivisions(): List<Division>
}

class SubscribeDivisionsRepositoryImpl @Inject constructor(
    private val divisionsService: DivisionsService,
    private val divisionDao: DivisionDao,
    private val internetChecker: InternetChecker,
    private val divisionApiToDbMapper: DivisionApiToDbMapper,
    private val divisionDbToDomainMapper: DivisionDbToDomainMapper

) : DivisionsRepository {
    override suspend fun getDivisions(): List<Division> {
        if (internetChecker.isInternetAvailable()) {

                val divisionsDb = divisionsService.getDivisions().map {
                    divisionApiToDbMapper.invoke(it)
                }
                divisionDao.insertAll(
                    *divisionsDb.toTypedArray()
                )


        }

        return divisionDao.getAll().map { divisionDbToDomainMapper.invoke(it) }
    }

}