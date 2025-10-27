package org.alexgoesfishinn.timetablespbu.data.storage.mappers.program

import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb
import org.alexgoesfishinn.timetablespbu.domain.entities.Program
import javax.inject.Inject
/**
 * @author a.bylev
 */
class ProgramDbToDomainMapper @Inject constructor(): (ProgramDb) -> Program {
    override fun invoke(p1: ProgramDb): Program {
        return Program(
            programId =  p1.programId,
            yearName = p1.yearName
        )
    }
}