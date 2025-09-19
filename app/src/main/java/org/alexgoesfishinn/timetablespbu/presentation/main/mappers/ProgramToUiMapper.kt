package org.alexgoesfishinn.timetablespbu.presentation.main.mappers

import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramN
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramItem
import javax.inject.Inject

class ProgramToUiMapper @Inject constructor(): (ProgramN) -> ProgramItem {
    override fun invoke(p1: ProgramN): ProgramItem {
        return ProgramItem(
            programId = p1.programId,
            yearName = p1.yearName
        )
    }
}