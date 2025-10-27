package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.GroupEventsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface SubscribeGroupEventsUseCase {
    suspend fun getEvents(groupId: Long, weekMonday: String): GroupEvents
}

class SubscribeGroupEventsUseCaseImpl @Inject constructor(
    private val groupEventsRepository: GroupEventsRepository
): SubscribeGroupEventsUseCase{
    override suspend fun getEvents(groupId: Long, weekMonday: String): GroupEvents {
        return groupEventsRepository.getEvents(groupId, weekMonday)
    }
}