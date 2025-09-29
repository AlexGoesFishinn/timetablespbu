package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupDb.Companion.GROUP_TABLE_NAME


@Entity(tableName = GROUP_TABLE_NAME)
data class GroupDb(
    @PrimaryKey
    @ColumnInfo("Id")
    val groupId: Long,
    @ColumnInfo("Name")
    val groupName: String,
    @ColumnInfo("StudyForm")
    val groupStudyForm: String,
    @ColumnInfo("Profiles")
    val groupProfiles: String,
    @ColumnInfo("ProgramId")
    var programId: Long = 0L
){
    companion object{
        const val GROUP_TABLE_NAME = "groups_table"
    }
}