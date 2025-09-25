package org.alexgoesfishinn.timetablespbu.data.network.mappers.program

import org.alexgoesfishinn.timetablespbu.data.network.entities.ProgramApi
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb
import javax.inject.Inject

class ProgramApiToDbMapper @Inject constructor(): (ProgramApi) -> ProgramDb{
    override fun invoke(p1: ProgramApi): ProgramDb {
        return ProgramDb(
            yearName = p1.yearName,
            yearNumber =  p1.yearNumber,
            isEmpty = p1.isEmpty,
            divisionAlias = p1.divisionAlias,
            programId = p1.programId

        )
    }
}