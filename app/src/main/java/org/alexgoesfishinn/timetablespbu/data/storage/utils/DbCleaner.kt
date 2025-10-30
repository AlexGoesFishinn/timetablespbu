package org.alexgoesfishinn.timetablespbu.data.storage.utils

import org.alexgoesfishinn.timetablespbu.data.repo.GroupEventsRepository
import org.alexgoesfishinn.timetablespbu.data.repo.GroupsRepository

import org.alexgoesfishinn.timetablespbu.data.storage.database.AppDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface DbCleaner{
    suspend fun deleteOldEvents()

    suspend fun deleteAllData()
}

class DbCleanerImpl @Inject constructor(
    private val groupsRepository: GroupsRepository,
    private val groupEventsRepository: GroupEventsRepository,
    private val appDatabase: AppDatabase
): DbCleaner {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val thresholdToDelete: Long = 3L

    override suspend fun deleteOldEvents() {
        deleteEventsByDate()
        deleteEventsByUnusedGroup()
    }

    override suspend fun deleteAllData() {
        appDatabase.clearAllTables()
    }

    private suspend fun deleteEventsByDate() {
        val currentWeekMonday= LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1)
        val thresholdMonday = currentWeekMonday.minusMonths(thresholdToDelete)
        val weekMondaysInDb = groupEventsRepository.getWeekMondayOfAllEvents().map { LocalDate.parse(it, formatter) }
        val weekMondaysToDelete: MutableList<String> = mutableListOf()
        weekMondaysInDb.forEach {
            if(it.isBefore(thresholdMonday)){
                weekMondaysToDelete.add(it.format(formatter))
            }
        }
        groupEventsRepository.deleteByWeekMondaysList(weekMondaysToDelete)
    }

    private suspend fun deleteEventsByUnusedGroup() {
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