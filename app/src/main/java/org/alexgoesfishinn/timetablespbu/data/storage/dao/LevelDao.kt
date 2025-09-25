package org.alexgoesfishinn.timetablespbu.data.storage.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.coroutineScope
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb

@Dao
abstract class LevelDao {
    @Query("SELECT * FROM ${LevelDb.LEVEL_TABLE_NAME} WHERE alias = :alias")
    abstract suspend fun getAlias(alias: String): List<LevelDb>


    @Query("DELETE FROM ${LevelDb.LEVEL_TABLE_NAME} WHERE alias = :alias")
    abstract suspend fun deleteAlias(alias: String)


    suspend fun insertLevels(alias: String, vararg levels: LevelDb){
        deleteAlias(alias)
        levels.forEach { l -> l.alias = alias }
        levels.forEach { level ->
            coroutineScope {
                val levelId = insertLevel(level)
                Log.i(TAG, "levelId = $levelId")
                Log.i(TAG,"level.programCombinations = ${level.programCombinations}")
                insertProgramCombinations(levelId, *level.programCombinations.toTypedArray())
            }
        }

    }

    private suspend fun insertProgramCombinations(id: Long, vararg programCombinationDb: ProgramCombinationDb){
        programCombinationDb.forEach { pc -> pc.levelId = id }
        programCombinationDb.forEach {
            pc -> coroutineScope {
                val programCombinationId = insertProgramCombination(pc)
                insertPrograms(programCombinationId, *pc.programs.toTypedArray())
        }
        }
    }

    private suspend fun insertPrograms(id: Long, vararg programDb: ProgramDb){
        programDb.forEach { p -> p.programCombinationId = id }
        insertProgram(*programDb)
    }

    @Insert
    abstract suspend fun insertLevel(levelDb: LevelDb): Long

    @Insert
    abstract suspend fun insertProgramCombination(programCombinationDb: ProgramCombinationDb): Long

    @Insert
    abstract suspend fun insertProgram(vararg programDb: ProgramDb)

    private companion object{
        const val TAG = "LevelDao"
    }
}