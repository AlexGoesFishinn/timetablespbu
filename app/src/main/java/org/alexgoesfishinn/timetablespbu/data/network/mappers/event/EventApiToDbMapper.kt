package org.alexgoesfishinn.timetablespbu.data.network.mappers.event

import org.alexgoesfishinn.timetablespbu.data.network.entities.EventApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.location.LocationApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EventDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LocationDb
import javax.inject.Inject

class EventApiToDbMapper @Inject constructor(
    private val locationApiToDbMapper: LocationApiToDbMapper
): (EventApi) -> EventDb {
    override fun invoke(p1: EventApi): EventDb {
        val locations = mutableListOf<LocationDb>()
        p1.locations.forEach {
            locations.add(locationApiToDbMapper.invoke(it))
        }
        val event = EventDb(
            allDay = p1.allDay,
            contingentUnitName = p1.contingentUnitName,
            contingentUnitsDisplayTest = p1.contingentUnitsDisplayTest,
            dateTimeIntervalString = p1.dateTimeIntervalString,
            timeIntervalString = p1.timeIntervalString,
            divisionAndCourse = p1.divisionAndCourse,
            start = p1.start,
            end = p1.end,
            subject = p1.subject,
            displayDateTimeIntervalString = p1.displayDateTimeIntervalString,
            educatorDisplayText = p1.educatorDisplayText,
            educatorsWereReassigned = p1.educatorsWereReassigned,
            electiveDisciplineCount = p1.electiveDisciplineCount,
            hasEducators = p1.hasEducators,
            hasTheSameTimeAsPreviousItem = p1.hasTheSameTimeAsPreviousItem,
            withinTheSameDay = p1.withinTheSameDay,
            isAssigned = p1.isAssigned,
            isElective = p1.isElective,
            isCancelled = p1.isCancelled,
            isStudy = p1.isStudy,
            kindCode = p1.kindCode,
            locationDisplayText = p1.locationDisplayText,
            locationWasChanged = p1.locationWasChanged,
            timeWasChanged = p1.timeWasChanged
        )
        event.locations = locations
        return event
    }
}