package org.alexgoesfishinn.timetablespbu.presentation.main.model


data class ProgramCombinationItem (
    val programName: String,
    val programNameEng: String,
    val programs: List<ProgramItem>
)