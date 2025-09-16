package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.data.network.mappers.division.DivisionsApiToDomainMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.DivisionsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.domain.entities.DivisionN
import javax.inject.Inject

interface DivisionsRepository {
    suspend fun getDivision(): List<DivisionN>
}

class SubscribeDivisionsRepositoryImpl @Inject constructor(
    private val divisionsService: DivisionsService,
    private val internetChecker: InternetChecker,
    private val divisionsApiToDomainMapper: DivisionsApiToDomainMapper
): DivisionsRepository{
    override suspend fun getDivision(): List<DivisionN> {
        if(internetChecker.isInternetAvailable()){
            val divisionsApi = divisionsService.getDivisions()
            val divisionsDomain = mutableListOf<DivisionN>()
            divisionsApi.forEach { d -> divisionsDomain.add(divisionsApiToDomainMapper.invoke(d)) }
            return divisionsDomain
        } else{
            TODO("в случае отсутствия сети")
        }
    }
}