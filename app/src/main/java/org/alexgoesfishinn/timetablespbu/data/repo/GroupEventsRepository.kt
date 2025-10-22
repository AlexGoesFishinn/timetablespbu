package org.alexgoesfishinn.timetablespbu.data.repo

import android.util.Log
import org.alexgoesfishinn.timetablespbu.data.network.mappers.groupevents.GroupEventsApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DayDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.GroupEventsDao
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupEventsDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.groupevents.GroupEventsDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import javax.inject.Inject

interface GroupEventsRepository {
    suspend fun getEvents(groupId: Long, weekMonday: String): GroupEvents
}

class SubscribeGroupEventsRepositoryImpl @Inject constructor(
    private val eventsService: EventsService,
    private val internetChecker: InternetChecker,
    private val groupEventsDao: GroupEventsDao,
    private val dayDao: DayDao,
    private val groupEventsApiToDbMapper: GroupEventsApiToDbMapper,
    private val groupEventsDbToDomainMapper: GroupEventsDbToDomainMapper,
) : GroupEventsRepository{
    override suspend fun getEvents(groupId: Long, weekMonday: String): GroupEvents {
        if(internetChecker.isInternetAvailable()){
            try {
                val groupEvents = eventsService.getEvents(groupId, weekMonday)
                groupEventsDao.insertWeek(
                    groupEventsDb = groupEventsApiToDbMapper.invoke(groupEvents),
                    groupId = groupId,
                    weekMonday = weekMonday
                )
            } catch (re: RuntimeException){
                internetChecker.apiErrorOccurs()
                Log.e(TAG, "message = ${re.message}")
            }

        }

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
    companion object{
        const val TAG ="SubscribeGroupEventsRepositoryImpl"
    }
}