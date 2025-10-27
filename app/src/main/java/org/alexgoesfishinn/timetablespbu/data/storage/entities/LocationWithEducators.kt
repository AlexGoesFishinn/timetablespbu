package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.Embedded
import androidx.room.Relation
/**
 * @author a.bylev
 */
data class LocationWithEducators(
    @Embedded val locationDb: LocationDb,
    @Relation(
        parentColumn = "Id",
        entityColumn = "LocationId",
        entity = EducatorDb::class
    )
    val educators: List<EducatorDb>
)