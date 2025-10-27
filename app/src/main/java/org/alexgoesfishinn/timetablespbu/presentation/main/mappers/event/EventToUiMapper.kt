package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.event

import org.alexgoesfishinn.timetablespbu.domain.entities.Event
import org.alexgoesfishinn.timetablespbu.presentation.main.mappers.location.LocationToUiMapper
import org.alexgoesfishinn.timetablespbu.presentation.main.model.EventItem
import javax.inject.Inject
/**
 * @author a.bylev
 */
class EventToUiMapper @Inject constructor(
    private val locationToUiMapper: LocationToUiMapper
): (Event) -> EventItem {
    override fun invoke(p1: Event): EventItem {
        return EventItem(
            kindCode = p1.kindCode,
            start = p1.start,
            end = p1.end,
            subject = p1.subject,
            timeIntervalString = p1.timeIntervalString,
            dateTimeIntervalString = p1.dateTimeIntervalString,
            displayDateTimeIntervalString = p1.displayDateTimeIntervalString,
            locationDisplayText = p1.locationDisplayText,
            educatorDisplayText = p1.educatorDisplayText,
            hasEducators = p1.hasEducators,
            isCancelled = p1.isCancelled,
            contingentUnitName = p1.contingentUnitName,
            divisionAndCourse = p1.divisionAndCourse,
            isAssigned = p1.isAssigned,
            timeWasChanged = p1.timeWasChanged,
            locationWasChanged = p1.locationWasChanged,
            educatorsWereReassigned = p1.educatorsWereReassigned,
            electiveDisciplineCount = p1.electiveDisciplineCount,
            isElective = p1.isElective,
            hasTheSameTimeAsPreviousItem = p1.hasTheSameTimeAsPreviousItem,
            contingentUnitsDisplayTest = p1.contingentUnitsDisplayTest,
            isStudy = p1.isStudy,
            allDay = p1.allDay,
            withinTheSameDay = p1.withinTheSameDay,
            locations = p1.locations.map { locationToUiMapper.invoke(it) }
        )
    }
}