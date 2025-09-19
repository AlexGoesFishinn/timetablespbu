package org.alexgoesfishinn.timetablespbu.presentation.main.mappers

import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import org.alexgoesfishinn.timetablespbu.presentation.main.model.LevelItem
import org.alexgoesfishinn.timetablespbu.presentation.main.model.ProgramCombinationItem
import javax.inject.Inject

class LevelToUiMapper @Inject constructor(): (Level) -> LevelItem {
    override fun invoke(p1: Level): LevelItem {
        val programCombinations = mutableListOf<ProgramCombinationItem>()
        p1.programCombinations.forEach { pc -> programCombinations.add(ProgramCombinationToUiMapper().invoke(pc)) }
        return LevelItem(
            levelName = p1.levelName,
            levelNameEng = p1.levelNameEng,
            programCombinations = programCombinations
        )
    }

}