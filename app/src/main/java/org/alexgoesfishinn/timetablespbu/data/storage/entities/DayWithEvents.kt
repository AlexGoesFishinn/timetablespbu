package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

class DayWithEvents (
    @Embedded val day: DayDb,
    @Relation(
        parentColumn = "Id",
        entityColumn = "DayId",
        entity = EventDb::class
    )
    val events: List<EventDb>
)