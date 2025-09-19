package org.alexgoesfishinn.timetablespbu.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DivisionDao
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DivisionDb

@Database(
    version = 1,
    entities = [DivisionDb::class]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun divisionDao(): DivisionDao

    companion object{
        const val DATABASE_NAME = "timetable.db"
    }
}