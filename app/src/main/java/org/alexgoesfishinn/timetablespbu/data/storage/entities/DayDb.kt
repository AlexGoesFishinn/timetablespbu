package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DayDb.Companion.DAY_TABLE_NAME
/**
 * @author a.bylev
 */
@Entity(tableName = DAY_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = GroupEventsDb::class,
        parentColumns = ["Id"],
        childColumns = ["GroupEventsId"],
        onDelete = ForeignKey.CASCADE
    )])
data class DayDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id: Long = 0L,
    @ColumnInfo("Day")
    val dateString:String,
    @ColumnInfo("DayString")
    val name: String,
    @ColumnInfo("GroupEventsId")
    var groupsEventsId: Long = 0L
) {
    @Ignore
    var events: List<EventDb> = emptyList()
    companion object{
        const val DAY_TABLE_NAME = "days_table"
    }
}