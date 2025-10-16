package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.programcombination

import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramCombinationItem
import javax.inject.Inject

class ProgramCombinationToUiMapper @Inject constructor(): (ProgramCombination) -> ProgramCombinationItem {
    override fun invoke(p1: ProgramCombination): ProgramCombinationItem {
//        val programs = mutableListOf<ProgramItem>()
//        p1.programs.forEach { p -> programs.add(ProgramToUiMapper().invoke(p)) }
        return ProgramCombinationItem(
            programName = p1.programName,
            programNameEng = p1.programNameEng,
            programCombinationId = p1.programCombinationId
//            programs = programs
        )
    }
}