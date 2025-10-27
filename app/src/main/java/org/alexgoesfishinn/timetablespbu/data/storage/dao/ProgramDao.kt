package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb
/**
 * @author a.bylev
 */
@Dao
interface ProgramDao {
    @Query("SELECT * FROM ${ProgramDb.PROGRAM_TABLE_NAME} WHERE ProgramCombinationId = :programCombinationId")
    suspend fun getProgramCombinationId(programCombinationId: Long): List<ProgramDb>


}