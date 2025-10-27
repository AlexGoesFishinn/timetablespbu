package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation
/**
 * @author a.bylev
 */
data class EventWithLocations(
    @Embedded val event: EventDb,
    @Relation(
        parentColumn = "Id",
        entityColumn = "EventId",
        entity = LocationDb::class
    )
    val locations: List<LocationDb>
)