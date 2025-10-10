package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EventDb.Companion.EVENT_TABLE_NAME

@Entity(tableName = EVENT_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = DayDb::class,
        parentColumns = ["Id"],
        childColumns = ["DayId"],
        onDelete = ForeignKey.CASCADE
    )])
data class EventDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id: Long = 0L,
    @ColumnInfo("StudyEventsTimeTableKindCode")
    val kindCode: Int,
    @ColumnInfo("Start")
    val start: String,
    @ColumnInfo("End")
    val end: String,
    @ColumnInfo("Subject")
    val subject: String,
    @ColumnInfo("TimeIntervalString")
    val timeIntervalString: String,
    @ColumnInfo("DateWithTimeIntervalString")
    val dateTimeIntervalString: String,
    @ColumnInfo("DisplayDateAndTimeIntervalString")
    val displayDateTimeIntervalString: String,
    @ColumnInfo("LocationsDisplayText")
    val locationDisplayText: String,
    @ColumnInfo("EducatorsDisplayText")
    val educatorDisplayText: String,
    @ColumnInfo("HasEducators")
    val hasEducators: Boolean,
    @ColumnInfo("IsCancelled")
    val isCancelled: Boolean,
    @ColumnInfo("ContingentUnitName")
    val contingentUnitName: String,
    @ColumnInfo("DivisionAndCourse")
    val divisionAndCourse: String,
    @ColumnInfo("IsAssigned")
    val isAssigned: Boolean,
    @ColumnInfo("TimeWasChanged")
    val timeWasChanged: Boolean,
    @ColumnInfo("LocationsWereChanged")
    val locationWasChanged: Boolean,
    @ColumnInfo("EducatorsWereReassigned")
    val educatorsWereReassigned: Boolean,
    @ColumnInfo("ElectiveDisciplinesCount")
    val electiveDisciplineCount: Int,
    @ColumnInfo("IsElective")
    val isElective: Boolean,
    @ColumnInfo("HasTheSameTimeAsPreviousItem")
    val hasTheSameTimeAsPreviousItem: Boolean,
    @ColumnInfo("ContingentUnitsDisplayTest")
    val contingentUnitsDisplayTest: String?,
    @ColumnInfo("IsStudy")
    val isStudy: Boolean,
    @ColumnInfo("AllDay")
    val allDay: Boolean,
    @ColumnInfo("WithinTheSameDay")
    val withinTheSameDay: Boolean,
    @ColumnInfo("DayId")
    var dayId: Long = 0L

) {
    @Ignore
    var locations: List<LocationDb> = emptyList()
//    @Ignore
//    var educators: List<EducatorDb> = emptyList()

    companion object{
        const val EVENT_TABLE_NAME = "events_table"
    }
}