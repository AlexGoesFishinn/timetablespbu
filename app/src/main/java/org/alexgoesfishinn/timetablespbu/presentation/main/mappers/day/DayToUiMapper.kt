package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.day

import org.alexgoesfishinn.timetablespbu.domain.entities.Day
import org.alexgoesfishinn.timetablespbu.presentation.main.model.DayItem
import javax.inject.Inject

class DayToUiMapper @Inject constructor(

): (Day) -> DayItem {
    override fun invoke(p1: Day): DayItem {
        return DayItem(
            id = p1.id,
            dateString = p1.dateString,
            name = p1.name,

        )
    }
}