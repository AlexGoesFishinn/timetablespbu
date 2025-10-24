package org.alexgoesfishinn.timetablespbu.data.repo

import android.util.Log
import org.alexgoesfishinn.timetablespbu.data.network.mappers.group.GroupApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.GroupsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.network.utils.WarningsNotificator
import org.alexgoesfishinn.timetablespbu.data.storage.dao.GroupDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.group.GroupDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Group
import java.io.IOException
import javax.inject.Inject

interface GroupsRepository {
    suspend fun getGroups(id: Long): List<Group>
}

class SubscribeGroupsRepositoryImpl @Inject constructor(
    private val internetChecker: InternetChecker,
    private val groupsService: GroupsService,
    private val groupApiToDbMapper: GroupApiToDbMapper,
    private val groupDao: GroupDao,
    private val groupDbToDomainMapper: GroupDbToDomainMapper,
    private val warningsNotificator: WarningsNotificator
): GroupsRepository{
    override suspend fun getGroups(id: Long): List<Group> {
        if(internetChecker.isInternetAvailable()){
            try {
                val groups = groupsService.getGroups(id).groups.map { groupApiToDbMapper.invoke(it) }
                groups.forEach { it.programId = id }
                groupDao.insertAll(id, *groups.toTypedArray())
            } catch (ioe: IOException) {warningsNotificator.apiErrorNotify()
                Log.e(TAG, "message = ${ioe.message}")}

        } else{warningsNotificator.internetIsNotAvailableNotify()}
        return groupDao.getId(id).map { groupDbToDomainMapper.invoke(it) }
    }

    companion object{
        const val TAG = "SubscribeGroupsRepositoryImpl"
    }
}