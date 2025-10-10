package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation

data class GroupEventsWithDays (
    @Embedded val groupEvents: GroupEventsDb,
    @Relation(
        entity = DayDb::class,
        parentColumn = "Id",
        entityColumn = "GroupEventsId"
    )
    val days: List<DayDb>
)