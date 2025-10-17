package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.FavouriteDb

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM ${FavouriteDb.FAVOURITE_TABLE_NAME}")
    suspend fun selectAll(): List<FavouriteDb>

    @Insert
    suspend fun insert(favouriteDb: FavouriteDb): Long

    @Query("DELETE FROM ${FavouriteDb.FAVOURITE_TABLE_NAME} WHERE Id = :id")
    suspend fun delete(id: Long)
}