package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DayDb
/**
 * @author a.bylev
 */
@Dao
interface DayDao {
    @Query("SELECT * FROM ${DayDb.DAY_TABLE_NAME} WHERE GroupEventsId = :groupEventsId")
    suspend fun getDays(groupEventsId: Long): List<DayDb>
}