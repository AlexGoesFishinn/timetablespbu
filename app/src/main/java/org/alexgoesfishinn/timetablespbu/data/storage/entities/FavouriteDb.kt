package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.FavouriteDb.Companion.FAVOURITE_TABLE_NAME

@Entity(tableName = FAVOURITE_TABLE_NAME)
data class FavouriteDb(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: Long,
    @ColumnInfo(name = "DisplayName")
    val displayName: String

){
    companion object{
        const val FAVOURITE_TABLE_NAME = "favourite_table"
    }
}
