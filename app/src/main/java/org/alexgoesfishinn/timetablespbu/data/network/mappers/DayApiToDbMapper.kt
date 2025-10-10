package org.alexgoesfishinn.timetablespbu.data.network.mappers

import org.alexgoesfishinn.timetablespbu.data.network.entities.DayApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.event.EventApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DayDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EventDb
import javax.inject.Inject

class DayApiToDbMapper @Inject constructor(
    private val eventApiToDbMapper: EventApiToDbMapper
): (DayApi) -> DayDb {
    override fun invoke(p1: DayApi): DayDb {
        val events = mutableListOf<EventDb>()
        p1.events.forEach {
            events.add(eventApiToDbMapper.invoke(it))
        }
        val day = DayDb(
            dateString = p1.dateString,
            name = p1.name
        )
        day.events = events
        return day
    }
}