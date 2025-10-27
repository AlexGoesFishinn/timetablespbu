package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.data.storage.dao.EducatorDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.EventDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.LocationDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.event.EventDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Event
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface EventsRepository {
    suspend fun getEvents(dayId: Long): List<Event>
}

class SubscribeEventsRepositoryImpl @Inject constructor(
    private val eventDao: EventDao,
    private val locationDao: LocationDao,
    private val educatorDao: EducatorDao,
    private val eventDbToDomainMapper: EventDbToDomainMapper
) : EventsRepository {
    override suspend fun getEvents(dayId: Long): List<Event> {
        val eventsDb = eventDao.getEvents(dayId)
        eventsDb.forEach { e ->
            e.locations = locationDao.getLocations(e.id)
            e.locations.forEach { l ->
                l.educators = educatorDao.getEducators(l.id)
            }
        }
        return eventsDb.map { eventDbToDomainMapper.invoke(it) }
    }
}