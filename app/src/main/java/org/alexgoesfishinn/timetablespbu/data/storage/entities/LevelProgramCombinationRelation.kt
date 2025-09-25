package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class LevelProgramCombinationRelation (
    @Embedded val levelDb: LevelDb,
    @Relation(
        parentColumn = "Id",
        entityColumn = "Level_id",
        entity = ProgramCombinationDb::class
    )
    val programCombinationDb: List<ProgramCombinationDb>
)