package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.program

import org.alexgoesfishinn.timetablespbu.domain.entities.Program
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramItem
import javax.inject.Inject
/**
 * @author a.bylev
 */
class ProgramToUiMapper @Inject constructor(): (Program) -> ProgramItem {
    override fun invoke(p1: Program): ProgramItem {
        return ProgramItem(
            programId = p1.programId,
            yearName = p1.yearName
        )
    }
}