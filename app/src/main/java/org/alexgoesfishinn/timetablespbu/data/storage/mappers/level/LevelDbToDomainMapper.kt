package org.alexgoesfishinn.timetablespbu.data.storage.mappers.level

import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelDb
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import javax.inject.Inject
/**
 * @author a.bylev
 */
class LevelDbToDomainMapper @Inject constructor(): (LevelDb) -> Level {
    override fun invoke(p1: LevelDb): Level {
        return Level(
            levelName =  p1.levelName,
            levelNameEng = p1.levelNameEng,
            levelId = p1.id,
        )
    }
}