package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EventDb

@Dao
interface EventDao {
    @Query("SELECT * FROM ${EventDb.EVENT_TABLE_NAME} WHERE DayId = :dayId")
    suspend fun getEvents(dayId: Long): List<EventDb>
}