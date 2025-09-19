package org.alexgoesfishinn.timetablespbu.data.network.mappers.level

import org.alexgoesfishinn.timetablespbu.data.network.entities.ProgramCombinationApi
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombinationN
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramN
import javax.inject.Inject

class ProgramCombinationApiToDomainMapper @Inject constructor(): (ProgramCombinationApi) -> ProgramCombinationN {
    override fun invoke(p1: ProgramCombinationApi): ProgramCombinationN {
        val programs = mutableListOf<ProgramN>()
        p1.programs.forEach { p -> programs.add(ProgramApiToDomainMapper().invoke(p)) }
        return ProgramCombinationN (
            programName = p1.programName,
            programNameEng = p1.programNameEng,
            programs = programs
        )
    }
}