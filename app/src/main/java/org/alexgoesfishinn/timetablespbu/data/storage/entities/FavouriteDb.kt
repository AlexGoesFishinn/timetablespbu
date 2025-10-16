package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.FavouriteDb.Companion.FAVOURITE_TABLE_NAME

@Entity(tableName = FAVOURITE_TABLE_NAME)
data class FavouriteDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Oid")
    val oid: Long = Long.MIN_VALUE,
    @ColumnInfo(name = "DisplayName")
    val displayName: String,
    @ColumnInfo(name = "Id")
    val id: Long,
){
    companion object{
        const val FAVOURITE_TABLE_NAME = "favourite_table"
    }
}
