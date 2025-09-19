package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DivisionDb

@Dao
interface DivisionDao {
    @Query("SELECT * FROM ${DivisionDb.DIVISION_TABLE_NAME}")
    suspend fun getAll(): List<DivisionDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg divisionDB: DivisionDb)
}