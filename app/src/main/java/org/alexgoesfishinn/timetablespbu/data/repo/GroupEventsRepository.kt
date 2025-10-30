package org.alexgoesfishinn.timetablespbu.data.repo

import android.util.Log
import org.alexgoesfishinn.timetablespbu.data.network.mappers.groupevents.GroupEventsApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.network.utils.WarningsNotificator
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DayDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.GroupEventsDao
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupEventsDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.groupevents.GroupEventsDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface GroupEventsRepository {
    suspend fun getEvents(groupId: Long, weekMonday: String): GroupEvents

    suspend fun deleteByGroupIdList(ids: List<Long>)

    suspend fun getGroupIdsOfAllEvents(): List<Long>

    suspend fun getWeekMondayOfAllEvents(): List<String>

    suspend fun deleteByWeekMondaysList(weekMondays: List<String>)


}

class SubscribeGroupEventsRepositoryImpl @Inject constructor(
    private val eventsService: EventsService,
    private val internetChecker: InternetChecker,
    private val groupEventsDao: GroupEventsDao,
    private val dayDao: DayDao,
    private val groupEventsApiToDbMapper: GroupEventsApiToDbMapper,
    private val groupEventsDbToDomainMapper: GroupEventsDbToDomainMapper,
    private val warningsNotificator: WarningsNotificator
) : GroupEventsRepository{


    override suspend fun getEvents(groupId: Long, weekMonday: String): GroupEvents {
        if(internetChecker.isInternetAvailable()){
            if(isGroupAvailable(groupId)){
                try {
                    val groupEvents = eventsService.getEvents(groupId, weekMonday)
                    groupEventsDao.insertWeek(
                        groupEventsDb = groupEventsApiToDbMapper.invoke(groupEvents),
                        groupId = groupId,
                        weekMonday = weekMonday
                    )
                } catch (ste: SocketTimeoutException){
                    warningsNotificator.serverTimeoutNotify()
                    Log.e(TAG, "ste message = ${ste.message}")
                }
                catch (ioe: IOException) {warningsNotificator.apiErrorNotify()
                    Log.e(TAG, "message = ${ioe.message}")}
            } else{warningsNotificator.groupIsNotAvailableNotify()}


        } else{warningsNotificator.internetIsNotAvailableNotify()}

        val groupEventDb = groupEventsDao.getWeek(groupId, weekMonday)
        if (groupEventDb == null){
            return groupEventsDbToDomainMapper.invoke(GroupEventsDb(groupId = groupId))
        }
        else{
            val days = dayDao.getDays(groupEventDb.id)
            groupEventDb.days = days
            return  groupEventsDbToDomainMapper.invoke(
                groupEventDb
            )

        }

    }

    override suspend fun getWeekMondayOfAllEvents(): List<String> {
        return groupEventsDao.getAllGroupEvents().map { it.weekMonday }
    }

    override suspend fun deleteByWeekMondaysList(weekMondays: List<String>) {
        groupEventsDao.deleteByWeekMondayList(weekMondays)
    }

    override suspend fun deleteByGroupIdList(ids: List<Long>) {
        groupEventsDao.deleteByGroupIdList(ids)
    }

    override suspend fun getGroupIdsOfAllEvents(): List<Long> {
        return groupEventsDao.getAllGroupEvents().map { it.groupId }
    }

    private suspend fun isGroupAvailable(groupId:Long): Boolean{
        Log.i(TAG, "isGroupAvailable method response = ${eventsService.checkGroup(groupId).code()}")
        return eventsService.checkGroup(groupId).code() != 404


    }

    companion object{
        const val TAG ="SubscribeGroupEventsRepositoryImpl"
    }
}