package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.GroupEventsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.GroupsRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import javax.inject.Inject

interface DeleteOldEventsUseCase {
    suspend fun deleteEventsByDate()

    suspend fun deleteEventsByUnusedGroup()
}

class DeleteOldEventsUseCaseImpl @Inject constructor(
    private val groupsRepository: GroupsRepository,
    private val groupEventsRepository: GroupEventsRepository
) : DeleteOldEventsUseCase{
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override suspend fun deleteEventsByDate() {
        val currentWeekMonday= LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1)
        val thresholdMonday = currentWeekMonday.minusMonths(3)
        val weekMondaysInDb = groupEventsRepository.getWeekMondayOfAllEvents().map { LocalDate.parse(it, formatter) }
        val weekMondaysToDelete: MutableList<String> = mutableListOf()
        weekMondaysInDb.forEach {
            if(it.isBefore(thresholdMonday)){
                weekMondaysToDelete.add(it.format(formatter))
            }
        }
        groupEventsRepository.deleteByWeekMondaysList(weekMondaysToDelete)
    }

    override suspend fun deleteEventsByUnusedGroup() {
        val groupIdsToDelete: MutableList<Long> = mutableListOf()
        val groupIdsInGroupsTable = groupsRepository.getAllGroupIds()
        val groupIdsInEventsTable = groupEventsRepository.getGroupIdsOfAllEvents()
        groupIdsInEventsTable.forEach {
            if(!groupIdsInGroupsTable.contains(it)){
                groupIdsToDelete.add(it)
            }
        }
        groupEventsRepository.deleteByGroupIdList(groupIdsToDelete)
    }
}