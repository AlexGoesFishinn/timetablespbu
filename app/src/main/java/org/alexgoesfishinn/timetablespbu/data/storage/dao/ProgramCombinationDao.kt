package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
/**
 * @author a.bylev
 */
@Dao
interface ProgramCombinationDao {
    @Query("SELECT * FROM ${ProgramCombinationDb.PROGRAM_COMBINATION_TABLE_NAME} WHERE Level_id = :levelId")
    suspend fun getLevelId(levelId: Long): List<ProgramCombinationDb>

    @Query("SELECT * FROM ${ProgramCombinationDb.PROGRAM_COMBINATION_TABLE_NAME}")
    suspend fun getAll(): List<ProgramCombinationDb>


}