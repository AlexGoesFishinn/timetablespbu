package org.alexgoesfishinn.timetablespbu.data.network.mappers.programcombination

import org.alexgoesfishinn.timetablespbu.data.network.entities.ProgramCombinationApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.program.ProgramApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramDb
import javax.inject.Inject

class ProgramCombinationApiToDbMapper @Inject constructor(
    private val programApiToDbMapper: ProgramApiToDbMapper
): (ProgramCombinationApi) -> ProgramCombinationDb{
    override fun invoke(p1: ProgramCombinationApi): ProgramCombinationDb {
        val programs = mutableListOf<ProgramDb>()
        p1.programs.forEach {
            programs.add(programApiToDbMapper.invoke(it))
        }
        val programCombinationDb = ProgramCombinationDb(
            programName = p1.programName,
            programNameEng = p1.programNameEng,

        )
        programCombinationDb.programs = programs
        return programCombinationDb
    }
}