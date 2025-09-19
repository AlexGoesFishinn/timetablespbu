package org.alexgoesfishinn.timetablespbu.presentation.main.model

import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombinationN

data class LevelItem (
    val levelName: String,
    val levelNameEng: String,
    val programCombinations: List<ProgramCombinationItem>
)