package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.level

import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import org.alexgoesfishinn.timetablespbu.presentation.main.model.LevelItem
import javax.inject.Inject
/**
 * @author a.bylev
 */
class LevelToUiMapper @Inject constructor(): (Level) -> LevelItem {
    override fun invoke(p1: Level): LevelItem {

        return LevelItem(
            levelName = p1.levelName,
            levelNameEng = p1.levelNameEng,
            levelId = p1.levelId,

        )
    }

}