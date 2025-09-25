package org.alexgoesfishinn.timetablespbu.data.storage.mappers.program

import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramN
import javax.inject.Inject

class ProgramDbToDomainMapper @Inject constructor(): (ProgramDb) -> ProgramN {
    override fun invoke(p1: ProgramDb): ProgramN {
        return ProgramN(
            programId =  p1.programId,
            yearName = p1.yearName
        )
    }
}