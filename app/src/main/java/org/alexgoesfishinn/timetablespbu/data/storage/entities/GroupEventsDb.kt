package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupEventsDb.Companion.GROUP_EVENTS_TABLE_NAME

@Entity(tableName = GROUP_EVENTS_TABLE_NAME)
data class GroupEventsDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id: Long = 0L,
    @ColumnInfo("GroupId")
    val groupId: Long,
    @ColumnInfo("StudentGroupDisplayName")
    val groupName: String,
    @ColumnInfo("TimeTableDisplayName")
    val timeTableDisplayName: String,
    @ColumnInfo("PreviousWeekMonday")
    val previousWeekMonday: String,
    @ColumnInfo("NextWeekMonday")
    val nextWeekMonday: String,
    @ColumnInfo("IsPreviousWeekReferenceAvailable")
    val isPreviousWeekReferenceAvailable: Boolean,
    @ColumnInfo("IsNextWeekReferenceAvailable")
    val isNextWeekReferenceAvailable: Boolean,
    @ColumnInfo("IsCurrentWeekReferenceAvailable")
    val isCurrentWeekReferenceAvailable: Boolean,
    @ColumnInfo("WeekDisplayText")
    val weekDisplayText: String,
    @ColumnInfo("WeekMonday")
    val weekMonday: String,

) {
    @Ignore
    var days: List<DayDb> = emptyList()

    companion object{
        const val GROUP_EVENTS_TABLE_NAME = "group_events_table"
    }
}