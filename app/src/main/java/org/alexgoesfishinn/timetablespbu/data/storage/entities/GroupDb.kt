package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class GroupDb(
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
)