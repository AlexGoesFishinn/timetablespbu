package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.LevelsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.Level
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface SubscribeLevelsUseCase {
    suspend fun getLevels(alias: String): List<Level>
}

class SubscribeLevelsUseCaseImpl @Inject constructor(
    private val levelsRepository: LevelsRepository
): SubscribeLevelsUseCase{
    override suspend fun getLevels(alias: String): List<Level> {
        return levelsRepository.getLevels(alias)
    }

}