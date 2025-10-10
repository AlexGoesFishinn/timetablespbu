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
//    private val divisionsApiToDomainMapper: DivisionsApiToDomainMapper,
    private val divisionApiToDbMapper: DivisionApiToDbMapper,
    private val divisionDbToDomainMapper: DivisionDbToDomainMapper

) : DivisionsRepository {
    override suspend fun getDivisions(): List<Division> {
        if (internetChecker.isInternetAvailable()) {
//            val divisionsDb = mutableListOf<DivisionDb>()
//            divisionsService.getDivisions().forEach { d -> divisionsDb.add(divisionApiToDbMapper.invoke(d)) }
//            divisionDao.insertAll(*divisionsDb.toTypedArray())
//            Log.i("DivisionRepository", "internetchecker = ${internetChecker.isInternetAvailable()}")
//            Log.i("DivisionRepository", "divisionsDb = $divisionsDb")
            val divisionsDb = divisionsService.getDivisions().map {
                divisionApiToDbMapper.invoke(it)
            }
            divisionDao.insertAll(
                *divisionsDb.toTypedArray()
            )
        }
//        val divisions = mutableListOf<Division>()
//        divisionDao.getAll().forEach { d -> divisions.add(divisionDbToDomainMapper.invoke(d)) }
//        Log.i("DivisionRepository", "divisions = $divisions")
//        return divisions
        return divisionDao.getAll().map { divisionDbToDomainMapper.invoke(it) }
    }
    //    override suspend fun getDivisions(): List<Division> {
//        if(internetChecker.isInternetAvailable()){
//            val divisionsApi = divisionsService.getDivisions()
//            val divisionsDomain = mutableListOf<Division>()
//            divisionsApi.forEach { d -> divisionsDomain.add(divisionsApiToDomainMapper.invoke(d)) }
//            return divisionsDomain
//        } else{
////            TODO("в случае отсутствия сети")
//            val divisionsApi = divisionsService.getDivisions()
//            val divisionsDomain = mutableListOf<Division>()
//            divisionsApi.forEach { d -> divisionsDomain.add(divisionsApiToDomainMapper.invoke(d)) }
//            return divisionsDomain
//        }
//    }
}