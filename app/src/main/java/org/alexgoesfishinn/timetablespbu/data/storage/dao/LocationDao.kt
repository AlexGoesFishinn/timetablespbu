package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LocationDb
/**
 * @author a.bylev
 */
@Dao
interface LocationDao {
    @Query("SELECT * FROM ${LocationDb.EVENT_LOCATION_TABLE_NAME} WHERE EventId = :eventId")
    suspend fun getLocations(eventId: Long): List<LocationDb>
}