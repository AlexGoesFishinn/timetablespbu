package org.alexgoesfishinn.timetablespbu.data.network.mappers.group

import org.alexgoesfishinn.timetablespbu.data.network.entities.GroupApi
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupDb
import javax.inject.Inject

class GroupApiToDbMapper @Inject constructor():(GroupApi) -> GroupDb {
    override fun invoke(p1: GroupApi): GroupDb {
        return GroupDb(
            groupId = p1.groupId,
            groupName = p1.groupName,
            groupStudyForm = p1.groupStudyForm,
            groupProfiles = p1.groupProfiles
        )
    }
}