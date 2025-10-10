package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.data.network.mappers.group.GroupApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.network.services.GroupsService
import org.alexgoesfishinn.timetablespbu.data.network.utils.InternetChecker
import org.alexgoesfishinn.timetablespbu.data.storage.dao.GroupDao
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.group.GroupDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Group
import javax.inject.Inject

interface GroupsRepository {
    suspend fun getGroups(id: Long): List<Group>
}

class SubscribeGroupsRepositoryImpl @Inject constructor(
    private val internetChecker: InternetChecker,
    private val groupsService: GroupsService,
    private val groupApiToDbMapper: GroupApiToDbMapper,
    private val groupDao: GroupDao,
    private val groupDbToDomainMapper: GroupDbToDomainMapper

): GroupsRepository{
    override suspend fun getGroups(id: Long): List<Group> {
        if(internetChecker.isInternetAvailable()){
            val groups = groupsService.getGroups(id).groups.map { groupApiToDbMapper.invoke(it) }
            groups.forEach { it.programId = id }
            groupDao.insertAll(id, *groups.toTypedArray())
        }
        return groupDao.getId(id).map { groupDbToDomainMapper.invoke(it) }
    }
}