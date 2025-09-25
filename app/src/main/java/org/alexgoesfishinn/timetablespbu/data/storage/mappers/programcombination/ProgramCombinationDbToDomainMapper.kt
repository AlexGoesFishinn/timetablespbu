package org.alexgoesfishinn.timetablespbu.data.storage.mappers.programcombination

import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.program.ProgramDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombinationN
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramN
import javax.inject.Inject

class ProgramCombinationDbToDomainMapper @Inject constructor(): (ProgramCombinationDb) -> ProgramCombinationN {
    override fun invoke(p1: ProgramCombinationDb): ProgramCombinationN {
//        val programs = mutableListOf<ProgramN>()
//        p1.programs.forEach { p -> programs.add(ProgramDbToDomainMapper().invoke(p)) }
        return ProgramCombinationN(
            programName = p1.programName,
            programNameEng = p1.programNameEng,
            programCombinationId = p1.id,
//            programs = programs
        )
    }
}