package org.alexgoesfishinn.timetablespbu.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DayDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DivisionDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.EducatorDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.EventDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.GroupDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.GroupEventsDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.LevelDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.LocationDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramCombinationDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramDao
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DayDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DivisionDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EducatorDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EventDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupEventsDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LocationDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb

@Database(
    version = 1,
    entities = [
        DivisionDb::class,
        LevelDb::class,
        ProgramCombinationDb::class,
        ProgramDb::class,
        GroupDb::class,
        GroupEventsDb::class,
        DayDb::class,
        EventDb::class,
        LocationDb::class,
        EducatorDb::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun divisionDao(): DivisionDao

    abstract fun levelDao(): LevelDao

    abstract fun programCombinationDao(): ProgramCombinationDao

    abstract fun programDao(): ProgramDao

    abstract fun groupDao(): GroupDao

    abstract fun groupEventsDao(): GroupEventsDao

    abstract fun dayDao(): DayDao

    abstract fun eventDao(): EventDao

    abstract fun locationDao(): LocationDao

    abstract fun educatorDao(): EducatorDao

    companion object {
        const val DATABASE_NAME = "timetable.db"
    }
}