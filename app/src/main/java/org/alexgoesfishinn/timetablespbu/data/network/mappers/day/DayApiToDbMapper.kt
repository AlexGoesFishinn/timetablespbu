package org.alexgoesfishinn.timetablespbu.data.network.mappers.day

import org.alexgoesfishinn.timetablespbu.data.network.entities.DayApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.event.EventApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DayDb

import javax.inject.Inject
/**
 * @author a.bylev
 */
class DayApiToDbMapper @Inject constructor(
    private val eventApiToDbMapper: EventApiToDbMapper
): (DayApi) -> DayDb {
    override fun invoke(p1: DayApi): DayDb {
        val events = p1.events.map { eventApiToDbMapper.invoke(it) }
        val day = DayDb(
            dateString = p1.dateString,
            name = p1.name
        )
        day.events = events
        return day
    }
}