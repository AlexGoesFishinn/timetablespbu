package org.alexgoesfishinn.timetablespbu.data.repo

import org.alexgoesfishinn.timetablespbu.domain.entities.Group
import javax.inject.Inject

interface GroupsRepository {
    suspend fun getGroups(id: Long): List<Group>
}

class GroupsRepositoryImpl @Inject constructor(

): GroupsRepository{
    override suspend fun getGroups(id: Long): List<Group> {
        TODO("Not yet implemented")
    }
}