package org.alexgoesfishinn.timetablespbu.data.network.mappers.level

//import org.alexgoesfishinn.timetablespbu.data.network.mappers.programcombination.ProgramCombinationApiToDomainMapper

//class LevelApiToDomainMapper @Inject constructor(): (LevelApi) -> Level {
//    override fun invoke(p1: LevelApi): Level {
//        val programCombinations = mutableListOf<ProgramCombinationN>()
//        p1.programCombinations.forEach { pg -> programCombinations.add(
//            ProgramCombinationApiToDomainMapper().invoke(pg)) }
//
//        return Level(
//            levelName = p1.levelName,
//            levelNameEng = p1.levelNameEng,
////            programCombinations = programCombinations
//        )
//    }
//}