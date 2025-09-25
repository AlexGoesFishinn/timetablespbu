package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb.Companion.PROGRAM_TABLE_NAME

@Entity(tableName = PROGRAM_TABLE_NAME,
    foreignKeys = [ForeignKey(
        ProgramCombinationDb::class,
        parentColumns = ["Id"],
        childColumns = ["ProgramCombinationId"],
        onDelete = ForeignKey.CASCADE
    )])
data class ProgramDb (
    @PrimaryKey
    @ColumnInfo("StudyProgramId")
    val programId: Long,
    @ColumnInfo("YearName")
    val yearName: String,
    @ColumnInfo("YearNumber")
    val yearNumber: Int,
    @ColumnInfo("IsEmpty")
    val isEmpty: Boolean,
    @ColumnInfo("PublicDivisionAlias")
    val divisionAlias: String,
    @ColumnInfo("ProgramCombinationId")
    var programCombinationId: Long = 0L
) {
    companion object{
        const val PROGRAM_TABLE_NAME = "programs_table"
    }
}