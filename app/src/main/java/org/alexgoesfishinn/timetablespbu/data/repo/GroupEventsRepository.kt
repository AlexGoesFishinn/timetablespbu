package org.alexgoesfishinn.timetablespbu.data.repo

import android.util.Log
import org.alexgoesfishinn.timetablespbu.data.network.entities.GroupEventsApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.groupevents.GroupEventsApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.EventsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.network.utils.WarningsNotificator
import org.alexgoesfishinn.timetablespbu.data.storage.dao.DayDao
import org.alexgoesfishinn.timetablespbu.data.storage.dao.GroupEventsDao
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupEventsDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.groupevents.GroupEventsDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.GroupEvents
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
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
                } catch (ioe: IOException) {warningsNotificator.apiErrorNotify()
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

    private suspend fun isGroupAvailable(groupId:Long): Boolean{
        var result = true
        eventsService.checkGroup(groupId).enqueue(object : Callback<GroupEventsApi> {

            override fun onResponse(
                call: Call<GroupEventsApi>,
                response: Response<GroupEventsApi>
            ) {
                if(response.code() == 404){
                    result = false
                }
            }

            override fun onFailure(call: Call<GroupEventsApi>, t: Throwable) {
                Log.e(TAG, "isGroupAvailable method error ${t.message}")
            }
        })
        return result


    }

    companion object{
        const val TAG ="SubscribeGroupEventsRepositoryImpl"
    }
}