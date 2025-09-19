package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.DivisionsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.Division
import javax.inject.Inject

interface SubscribeDivisionsUseCase {
    suspend fun getDivisions(): List<Division>
}

class SubscribeDivisionsUseCaseImpl @Inject constructor(
    private val divisionsRepository: DivisionsRepository
): SubscribeDivisionsUseCase {
    override suspend fun getDivisions(): List<Division> {
        return divisionsRepository.getDivisions()
    }
}