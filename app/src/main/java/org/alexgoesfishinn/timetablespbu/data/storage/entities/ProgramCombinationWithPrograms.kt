package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ProgramCombinationWithPrograms (
    @Embedded val programCombinationDb: ProgramCombinationDb,
    @Relation(
        parentColumn = "Id",
        entityColumn = "ProgramCombinationId",
        entity = ProgramDb::class
    )
    val programDb: List<ProgramDb>
)