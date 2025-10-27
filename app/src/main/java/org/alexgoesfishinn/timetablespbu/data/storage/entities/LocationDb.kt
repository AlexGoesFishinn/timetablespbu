package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LocationDb.Companion.EVENT_LOCATION_TABLE_NAME
/**
 * @author a.bylev
 */
@Entity(tableName = EVENT_LOCATION_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = EventDb::class,
        parentColumns = ["Id"],
        childColumns = ["EventId"],
        onDelete = ForeignKey.CASCADE
    )])
data class LocationDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id: Long = 0L,
    @ColumnInfo("IsEmpty")
    val isEmpty: Boolean,
    @ColumnInfo("DisplayName")
    val displayName: String,
    @ColumnInfo("HasGeographicCoordinates")
    val hasGeographicCoordinates: Boolean,
    @ColumnInfo("Latitude")
    val latitude: Double,
    @ColumnInfo("Longitude")
    val longitude: Double,
    @ColumnInfo("LatitudeValue")
    val latitudeString: String,
    @ColumnInfo("LongitudeValue")
    val longitudeString: String,
    @ColumnInfo("EducatorsDisplayText")
    val educatorsDisplayText: String,
    @ColumnInfo("HasEducators")
    val hasEducators: String,
    @ColumnInfo("EventId")
    var eventId: Long = 0L
) {

    @Ignore
    var educators: List<EducatorDb> = emptyList()

    companion object{
        const val EVENT_LOCATION_TABLE_NAME = "event_locations_table"
}
}