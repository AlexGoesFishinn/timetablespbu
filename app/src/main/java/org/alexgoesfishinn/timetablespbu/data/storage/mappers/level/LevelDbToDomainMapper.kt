package org.alexgoesfishinn.timetablespbu.data.storage.mappers.level

import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.programcombination.ProgramCombinationDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombinationN
import javax.inject.Inject

class LevelDbToDomainMapper @Inject constructor(): (LevelDb) -> Level {
    override fun invoke(p1: LevelDb): Level {
//        val programCombinations = mutableListOf<ProgramCombinationN>()
//        p1.programCombinations.forEach {
//            pc -> programCombinations.add(ProgramCombinationDbToDomainMapper().invoke(pc))
//        }
        return Level(
            levelName =  p1.levelName,
            levelNameEng = p1.levelNameEng,
            levelId = p1.id,
//            programCombinations =programCombinations
        )
    }
}