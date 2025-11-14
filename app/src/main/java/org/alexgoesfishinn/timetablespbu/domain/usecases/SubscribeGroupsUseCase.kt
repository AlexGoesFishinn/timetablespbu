package org.alexgoesfishinn.timetablespbu.domain.usecases

import org.alexgoesfishinn.timetablespbu.data.repo.GroupsRepository
import org.alexgoesfishinn.timetablespbu.domain.entities.Group
import javax.inject.Inject
/**
 * @author a.bylev
 */
interface SubscribeGroupsUseCase {
    suspend fun getGroups(id: Long): List<Group>
}

class SubscribeGroupsUseCaseImpl @Inject constructor(
    private val groupsRepository: GroupsRepository
): SubscribeGroupsUseCase{
    override suspend fun getGroups(id: Long): List<Group> {
        return groupsRepository.getGroups(id).sortedBy { it.groupName }
    }
}