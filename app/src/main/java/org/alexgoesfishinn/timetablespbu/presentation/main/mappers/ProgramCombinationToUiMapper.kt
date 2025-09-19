package org.alexgoesfishinn.timetablespbu.presentation.main.mappers

import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombinationN
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramCombinationItem
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramItem
import javax.inject.Inject

class ProgramCombinationToUiMapper @Inject constructor(): (ProgramCombinationN) -> ProgramCombinationItem {
    override fun invoke(p1: ProgramCombinationN): ProgramCombinationItem {
        val programs = mutableListOf<ProgramItem>()
        p1.programs.forEach { p -> programs.add(ProgramToUiMapper().invoke(p)) }
        return ProgramCombinationItem(
            programName = p1.programName,
            programNameEng = p1.programNameEng,
            programs = programs
        )
    }
}