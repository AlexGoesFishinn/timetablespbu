package org.alexgoesfishinn.timetablespbu.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DivisionDb.Companion.DIVISION_TABLE_NAME

@Entity(tableName = DIVISION_TABLE_NAME)
data class DivisionDb (
    @PrimaryKey
    @ColumnInfo(name = "oid")
    val oid: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "alias")
    val alias: String
) {
    companion object{
        const val DIVISION_TABLE_NAME = "divisions_table"
    }
}