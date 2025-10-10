package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EducatorDb.Companion.EDUCATOR_TABLE_NAME

@Entity(tableName = EDUCATOR_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = LocationDb::class,
        childColumns = ["LocationId"],
        parentColumns = ["Id"],
        onDelete = ForeignKey.CASCADE
    )])
data class EducatorDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id: Long = 0L,
    @ColumnInfo("Item1")
    val item1: Int,
    @ColumnInfo("Name")
    val name: String,
    @ColumnInfo("LocationId")
    var locationId: Long = 0L
) {
    companion object{
        const val EDUCATOR_TABLE_NAME = "educators_table"
    }
}