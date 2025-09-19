package org.alexgoesfishinn.timetablespbu.domain.entities


data class Level (
    val levelName: String,
    val levelNameEng: String,
    val programCombinations: List<ProgramCombinationN>
)