package org.alexgoesfishinn.timetablespbu.data.network.mappers.level

import org.alexgoesfishinn.timetablespbu.data.network.entities.LevelApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.program.ProgramApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.mappers.programcombination.ProgramCombinationApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LevelDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb
import javax.inject.Inject

class LevelApiToDbMapper @Inject constructor(
    private val programCombinationApiToDbMapper: ProgramCombinationApiToDbMapper
): (LevelApi) -> LevelDb {
    override fun invoke(p1: LevelApi): LevelDb {
        val programCombinationsDb = mutableListOf<ProgramCombinationDb>()
        p1.programCombinations.forEach {
            programCombinationsDb.add(programCombinationApiToDbMapper.invoke(it))
        }
        val levelDb = LevelDb(
            levelName = p1.levelName,
            levelNameEng = p1.levelNameEng,
            hasCourse6 = p1.hasCourse6,


            )
        levelDb.programCombinations = programCombinationsDb
        return levelDb
    }
}