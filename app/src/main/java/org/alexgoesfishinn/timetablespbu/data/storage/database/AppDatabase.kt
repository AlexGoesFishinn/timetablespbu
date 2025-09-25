package org.alexgoesfishinn.timetablespbu.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DivisionDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.LevelDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramCombinationDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.ProgramDao
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DivisionDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelProgramCombinationRelation
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationProgramRelation
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb

@Database(
    version = 1,
    entities = [DivisionDb::class,
        LevelDb::class,
        ProgramCombinationDb::class,
        ProgramDb::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun divisionDao(): DivisionDao

    abstract fun levelDao(): LevelDao

    abstract fun programCombinationDao(): ProgramCombinationDao

    abstract fun programDao(): ProgramDao

    companion object {
        const val DATABASE_NAME = "timetable.db"
    }
}