package org.alexgoesfishinn.timetablespbu.domain.entities


data class ProgramCombinationN (
    val programName: String,
    val programNameEng: String,
    val programs: List<ProgramN>
)