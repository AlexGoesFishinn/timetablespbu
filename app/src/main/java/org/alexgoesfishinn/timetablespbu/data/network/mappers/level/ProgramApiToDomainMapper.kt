package org.alexgoesfishinn.timetablespbu.data.network.mappers.level

import org.alexgoesfishinn.timetablespbu.data.network.entities.ProgramApi
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramN
import javax.inject.Inject

class ProgramApiToDomainMapper @Inject constructor(): (ProgramApi) -> ProgramN {
    override fun invoke(p1: ProgramApi): ProgramN {
        return ProgramN(
            yearName = p1.yearName,
            programId = p1.programId
        )
    }
}