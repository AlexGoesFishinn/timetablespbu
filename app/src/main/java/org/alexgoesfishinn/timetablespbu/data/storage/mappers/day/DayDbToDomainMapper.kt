package org.alexgoesfishinn.timetablespbu.data.storage.mappers.day

import org.alexgoesfishinn.timetablespbu.data.storage.entities.DayDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.event.EventDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Day
import javax.inject.Inject

class DayDbToDomainMapper @Inject constructor(
    private val eventDbToDomainMapper: EventDbToDomainMapper
): (DayDb) -> Day {
    override fun invoke(p1: DayDb): Day {
        val events = p1.events.map { eventDbToDomainMapper.invoke(it) }
        val day = Day(
            id = p1.id,
            dateString = p1.dateString,
            name = p1.name,
            events = events
        )
        return day
    }
}