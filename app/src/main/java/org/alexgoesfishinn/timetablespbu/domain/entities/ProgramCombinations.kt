package org.alexgoesfishinn.timetablespbu.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProgramCombinations (
    val programCombinations: List<ProgramCombination>
)