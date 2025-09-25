package org.alexgoesfishinn.timetablespbu.domain.entities

data class LevelN (
    val levelName: String,
    val levelNameEng: String,
    val programCombinations: List<ProgramCombinationN>
)