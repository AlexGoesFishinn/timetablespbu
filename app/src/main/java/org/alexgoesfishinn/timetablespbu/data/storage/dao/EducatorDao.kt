package org.alexgoesfishinn.timetablespbu.data.storage.dao

import androidx.room.Dao
import androidx.room.Query
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EducatorDb

@Dao
interface EducatorDao {
    @Query("SELECT * FROM ${EducatorDb.EDUCATOR_TABLE_NAME} WHERE LocationId = :locationId")
    suspend fun getEducators(locationId: Long): List<EducatorDb>
}