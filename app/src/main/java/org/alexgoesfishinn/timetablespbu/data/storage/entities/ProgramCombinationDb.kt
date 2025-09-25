package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb.Companion.PROGRAM_COMBINATION_TABLE_NAME

@Entity(
    tableName = PROGRAM_COMBINATION_TABLE_NAME,
    foreignKeys = [ForeignKey(entity = LevelDb::class,
        parentColumns = ["Id"],
        childColumns = ["Level_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ProgramCombinationDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id: Long = 0L,
    @ColumnInfo("Name")
    val programName: String,
    @ColumnInfo("NameEnglish")
    val programNameEng: String,
//    @Ignore
//    val programs: List<ProgramDb>,
    @ColumnInfo("Level_id")
    var levelId: Long = 0L

) {

    @Ignore
    var programs: List<ProgramDb> = emptyList()
    companion object{
        const val PROGRAM_COMBINATION_TABLE_NAME = "program_combinations_table"
    }
}