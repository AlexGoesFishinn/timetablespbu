package org.alexgoesfishinn.timetablespbu.data.storage.mappers.groupevents

import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupEventsDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.day.DayDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import javax.inject.Inject
/**
 * @author a.bylev
 */
class GroupEventsDbToDomainMapper @Inject constructor(
    private val dayDbToDomainMapper: DayDbToDomainMapper
): (GroupEventsDb) -> GroupEvents {
    override fun invoke(p1: GroupEventsDb): GroupEvents {
        val days = p1.days.map { dayDbToDomainMapper.invoke(it) }
        val groupEvents = GroupEvents(
            groupId = p1.groupId,
            groupName = p1.groupName,
            weekMonday = p1.weekMonday,
            nextWeekMonday = p1.nextWeekMonday,
            previousWeekMonday = p1.previousWeekMonday,
            isCurrentWeekReferenceAvailable = p1.isCurrentWeekReferenceAvailable,
            isNextWeekReferenceAvailable = p1.isNextWeekReferenceAvailable,
            isPreviousWeekReferenceAvailable = p1.isPreviousWeekReferenceAvailable,
            timeTableDisplayName = p1.timeTableDisplayName,
            weekDisplayText = p1.weekDisplayText,
            days = days
        )
        return groupEvents
    }
}