package org.alexgoesfishinn.timetablespbu.data.network.mappers.programcombination

import org.alexgoesfishinn.timetablespbu.data.network.entities.ProgramCombinationApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.program.ProgramApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
import javax.inject.Inject
/**
 * @author a.bylev
 */
class ProgramCombinationApiToDbMapper @Inject constructor(
    private val programApiToDbMapper: ProgramApiToDbMapper
): (ProgramCombinationApi) -> ProgramCombinationDb{
    override fun invoke(p1: ProgramCombinationApi): ProgramCombinationDb {
        val programs = p1.programs.map { programApiToDbMapper.invoke(it) }
        val programCombinationDb = ProgramCombinationDb(
            programName = p1.programName,
            programNameEng = p1.programNameEng,

        )
        programCombinationDb.programs = programs
        return programCombinationDb
    }
}