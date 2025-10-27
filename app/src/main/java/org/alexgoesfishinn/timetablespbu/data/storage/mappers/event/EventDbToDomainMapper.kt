package org.alexgoesfishinn.timetablespbu.data.storage.mappers.event

import org.alexgoesfishinn.timetablespbu.data.storage.entities.EventDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.location.LocationDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Event
import javax.inject.Inject
/**
 * @author a.bylev
 */
class EventDbToDomainMapper @Inject constructor(
    private val locationDbToDomainMapper: LocationDbToDomainMapper
): (EventDb) -> Event {
    override fun invoke(p1: EventDb): Event {
        val locations = p1.locations.map { locationDbToDomainMapper.invoke(it) }
        val event = Event(
            allDay = p1.allDay,
            contingentUnitName = p1.contingentUnitName,
            contingentUnitsDisplayTest = p1.contingentUnitsDisplayTest,
            dateTimeIntervalString = p1.dateTimeIntervalString,
            timeIntervalString = p1.timeIntervalString,
            displayDateTimeIntervalString = p1.displayDateTimeIntervalString,
            divisionAndCourse = p1.divisionAndCourse,
            educatorDisplayText = p1.educatorDisplayText,
            locationDisplayText = p1.locationDisplayText,
            educatorsWereReassigned = p1.educatorsWereReassigned,
            electiveDisciplineCount = p1.electiveDisciplineCount,
            isElective = p1.isElective,
            isCancelled = p1.isCancelled,
            isAssigned = p1.isAssigned,
            isStudy = p1.isStudy,
            start = p1.start,
            end = p1.end,
            withinTheSameDay = p1.withinTheSameDay,
            hasEducators = p1.hasEducators,
            hasTheSameTimeAsPreviousItem = p1.hasTheSameTimeAsPreviousItem,
            kindCode = p1.kindCode,
            locationWasChanged = p1.locationWasChanged,
            subject = p1.subject,
            timeWasChanged = p1.timeWasChanged,
            locations = locations
        )
        return event
    }
}