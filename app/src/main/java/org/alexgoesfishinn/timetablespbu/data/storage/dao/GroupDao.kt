package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupDb

@Dao
abstract class GroupDao {
    @Insert
    abstract suspend fun insert(vararg groupsDb: GroupDb)

    @Query("DELETE FROM ${GroupDb.GROUP_TABLE_NAME} WHERE programId = :id")
    abstract suspend fun delete(id: Long)

    @Query("SELECT * FROM ${GroupDb.GROUP_TABLE_NAME} WHERE programId = :id")
    abstract suspend fun getId(id: Long): List<GroupDb>

    @Query("SELECT * FROM ${GroupDb.GROUP_TABLE_NAME}")
    abstract suspend fun getAllGroups(): List<GroupDb>

    suspend fun insertAll(id: Long, vararg groupsDb: GroupDb){
        delete(id)
        insert(*groupsDb)
    }


}