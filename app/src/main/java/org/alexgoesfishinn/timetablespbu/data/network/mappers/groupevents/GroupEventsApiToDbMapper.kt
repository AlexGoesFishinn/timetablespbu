package org.alexgoesfishinn.timetablespbu.data.network.mappers.groupevents

import org.alexgoesfishinn.timetablespbu.data.network.entities.GroupEventsApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.DayApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.DayDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupEventsDb
import javax.inject.Inject

class GroupEventsApiToDbMapper @Inject constructor(
    private val dayApiToDbMapper: DayApiToDbMapper
): (GroupEventsApi) -> GroupEventsDb {
    override fun invoke(p1: GroupEventsApi): GroupEventsDb {
        val days = mutableListOf<DayDb>()
        p1.days.forEach {
            days.add(dayApiToDbMapper.invoke(it))
        }
        val groupEvents = GroupEventsDb(
            groupId = p1.groupId,
            groupName = p1.groupName,
            isCurrentWeekReferenceAvailable = p1.isCurrentWeekReferenceAvailable,
            isPreviousWeekReferenceAvailable = p1.isPreviousWeekReferenceAvailable,
            isNextWeekReferenceAvailable = p1.isNextWeekReferenceAvailable,
            nextWeekMonday = p1.nextWeekMonday,
            weekMonday = p1.weekMonday,
            timeTableDisplayName = p1.timeTableDisplayName,
            previousWeekMonday = p1.previousWeekMonday,
            weekDisplayText = p1.weekDisplayText
        )
        groupEvents.days = days
        return groupEvents
    }
}