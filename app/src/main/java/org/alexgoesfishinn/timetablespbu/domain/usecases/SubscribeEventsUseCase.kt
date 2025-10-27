package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.EventsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.Event
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface SubscribeEventsUseCase{
    suspend fun getEvents(dayId: Long): List<Event>
}
class SubscribeEventsUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
): SubscribeEventsUseCase {
    override suspend fun getEvents(dayId: Long): List<Event> {
        return eventsRepository.getEvents(dayId)
    }
}