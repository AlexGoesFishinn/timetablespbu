package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore


@Entity
data class ProgramGroupsDb(
    @ColumnInfo("Id")
    val programId: Long

) {
    @Ignore
    var groups: List<GroupDb> = emptyList()
}