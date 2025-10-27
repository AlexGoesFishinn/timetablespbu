package org.alexgoesfishinn.timetablespbu.data.storage.mappers.programcombination

import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramCombinationDb
import org.alexgoesfishinn.timetablespbu.domain.entities.ProgramCombination
import javax.inject.Inject
/**
 * @author a.bylev
 */
class ProgramCombinationDbToDomainMapper @Inject constructor(): (ProgramCombinationDb) -> ProgramCombination {
    override fun invoke(p1: ProgramCombinationDb): ProgramCombination {

        return ProgramCombination(
            programName = p1.programName,
            programNameEng = p1.programNameEng,
            programCombinationId = p1.id,
        )
    }
}