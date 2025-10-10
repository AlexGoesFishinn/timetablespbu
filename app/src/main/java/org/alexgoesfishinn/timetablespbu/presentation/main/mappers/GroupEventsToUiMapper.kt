package org.alexgoesfishinn.timetablespbu.presentation.main.mappers

import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import org.alexgoesfishinn.timetablespbu.presentation.main.model.GroupEventsItem
import javax.inject.Inject

class GroupEventsToUiMapper @Inject constructor(
    private val dayToUiMapper: DayToUiMapper
): (GroupEvents) -> GroupEventsItem {
    override fun invoke(p1: GroupEvents): GroupEventsItem {
        return GroupEventsItem(
            groupId = p1.groupId,
            groupName = p1.groupName,
            timeTableDisplayName = p1.timeTableDisplayName,
            previousWeekMonday = p1.previousWeekMonday,
            nextWeekMonday = p1.nextWeekMonday,
            isPreviousWeekReferenceAvailable = p1.isPreviousWeekReferenceAvailable,
            isNextWeekReferenceAvailable = p1.isNextWeekReferenceAvailable,
            isCurrentWeekReferenceAvailable = p1.isCurrentWeekReferenceAvailable,
            weekDisplayText = p1.weekDisplayText,
            weekMonday = p1.weekMonday,
            days = p1.days.map { dayToUiMapper.invoke(it) }
        )
    }
}