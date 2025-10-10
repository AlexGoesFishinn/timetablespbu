package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DayDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EducatorDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EventDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupEventsDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LocationDb

@Dao
abstract class GroupEventsDao {
//    @Query("SELECT * FROM ${GroupEventsDb.GROUP_EVENTS_TABLE_NAME} WHERE GroupId = :groupId AND IsCurrentWeekReferenceAvailable = false")
//    abstract suspend fun getCurrentWeek(groupId: Long)

    @Query("SELECT * FROM ${GroupEventsDb.GROUP_EVENTS_TABLE_NAME} WHERE GroupId = :groupId AND WeekMonday = :weekMonday")
    abstract suspend fun getWeek(groupId: Long, weekMonday: String): GroupEventsDb

//    @Query("DELETE FROM ${GroupEventsDb.GROUP_EVENTS_TABLE_NAME} WHERE GroupId = :groupId AND IsCurrentWeekReferenceAvailable = false")
//    abstract suspend fun deleteCurrentWeek(groupId: Long)

    @Query("DELETE FROM ${GroupEventsDb.GROUP_EVENTS_TABLE_NAME} WHERE GroupId = :groupId AND WeekMonday = :weekMonday")
    abstract suspend fun deleteWeek(groupId: Long, weekMonday: String)

    @Insert
    abstract suspend fun insertGroupEvents(groupEventsDb: GroupEventsDb): Long

    @Insert
    abstract suspend fun insertDay(dayDb: DayDb): Long

    @Insert
    abstract suspend fun insertEvent(eventDb: EventDb): Long

    @Insert
    abstract suspend fun insertLocation(locationDb: LocationDb): Long

    @Insert
    abstract suspend fun insertEducators(vararg educatorDb: EducatorDb)

//    @Insert
//    abstract suspend fun insertEducator(educatorDb: EducatorDb): Long

    suspend fun insertWeek(groupEventsDb: GroupEventsDb, groupId: Long, weekMonday: String) {
        deleteWeek(groupId, weekMonday)
        val groupEventsId = insertGroupEvents(groupEventsDb)
        val days = groupEventsDb.days
        days.forEach {
            it.groupsEventsId = groupEventsId
        }
        insertDays(*days.toTypedArray())
    }

    private suspend fun insertDays(vararg days: DayDb){
        days.forEach {
            val dayId = insertDay(it)
            val events = it.events
            events.forEach { e -> e.dayId = dayId }
            insertEvents(*events.toTypedArray())
        }
    }

    private suspend fun insertEvents(vararg events: EventDb){
        events.forEach {
            val eventId = insertEvent(it)
            val locations = it.locations
            locations.forEach { l -> l.eventId = eventId }
            insertLocations(*locations.toTypedArray())
        }
    }

    private suspend fun insertLocations(vararg locations: LocationDb){
        locations.forEach {
            val locationId = insertLocation(it)
            val educators = it.educators
            educators.forEach { e -> e.locationId = locationId }
            insertEducators(*educators.toTypedArray())
        }
    }


}