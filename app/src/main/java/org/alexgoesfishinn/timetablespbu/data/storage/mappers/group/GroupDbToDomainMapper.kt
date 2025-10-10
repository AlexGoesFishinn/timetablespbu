package org.alexgoesfishinn.timetablespbu.data.storage.mappers.group

import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupDb
import org.alexgoesfishinn.timetablespbu.domain.entities.Group
import javax.inject.Inject

class GroupDbToDomainMapper @Inject constructor(): (GroupDb) -> Group {
    override fun invoke(p1: GroupDb): Group {
        return Group(
            groupId = p1.groupId,
            groupName = p1.groupName,
            groupProfiles = p1.groupProfiles,
            groupStudyForm = p1.groupStudyForm
        )
    }
}