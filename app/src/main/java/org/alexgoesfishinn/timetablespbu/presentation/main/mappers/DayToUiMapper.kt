package org.alexgoesfishinn.timetablespbu.presentation.main.mappers

import org.alexgoesfishinn.timetablespbu.domain.entities.Day
import org.alexgoesfishinn.timetablespbu.presentation.main.model.DayItem
import javax.inject.Inject

class DayToUiMapper @Inject constructor(
    private val eventToUiMapper: EventToUiMapper
): (Day) -> DayItem {
    override fun invoke(p1: Day): DayItem {
        return DayItem(
            dateString = p1.dateString,
            name = p1.name,
            events = p1.events.map { eventToUiMapper.invoke(it) }
        )
    }
}
