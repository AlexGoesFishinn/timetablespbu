package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelDb.Companion.LEVEL_TABLE_NAME
/**
 * @author a.bylev
 */
@Entity(
    tableName = LEVEL_TABLE_NAME
)
data class LevelDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id: Long = 0L,
    @ColumnInfo("StudyLevelName")
    val levelName: String,
    @ColumnInfo("StudyLevelNameEnglish")
    val levelNameEng: String,
    @ColumnInfo("HasCourse6")
    val hasCourse6: Boolean,
//    @Ignore
//    val programCombinations: List<ProgramCombinationDb>,
    @ColumnInfo("Alias")
    var alias: String = ""
) {
    @Ignore
    var programCombinations: List<ProgramCombinationDb> = emptyList()

    companion object{
        const val LEVEL_TABLE_NAME = "levels_table"
    }
}