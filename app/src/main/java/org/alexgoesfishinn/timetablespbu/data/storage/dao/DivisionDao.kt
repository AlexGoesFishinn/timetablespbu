package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DivisionDb
/**
 * @author a.bylev
 */
@Dao
abstract class DivisionDao {
    @Query("SELECT * FROM ${DivisionDb.DIVISION_TABLE_NAME}")
    abstract suspend fun getAll(): List<DivisionDb>


    suspend fun insertAll(vararg divisionDB: DivisionDb){
        deleteAll()
        insert(*divisionDB)
    }
    @Query("DELETE FROM ${DivisionDb.DIVISION_TABLE_NAME}")
    abstract suspend fun deleteAll()

    @Insert
    abstract suspend fun insert(vararg divisionDB: DivisionDb)

}