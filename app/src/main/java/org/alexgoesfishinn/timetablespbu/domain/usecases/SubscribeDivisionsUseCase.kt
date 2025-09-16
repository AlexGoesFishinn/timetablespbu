package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.DivisionN
import javax.inject.Inject

interface SubscribeDivisionsUseCase {
    suspend fun getDivisions(): List<DivisionN>
}

class SubscribeDivisionsUseCaseImpl @Inject constructor(
    private val divisionsRepository: DivisionsRepository
): SubscribeDivisionsUseCase {
    override suspend fun getDivisions(): List<DivisionN> {
        return divisionsRepository.getDivision()
    }
}