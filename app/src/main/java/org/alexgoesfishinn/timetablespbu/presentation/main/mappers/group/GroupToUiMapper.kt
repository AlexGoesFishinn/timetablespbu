package org.alexgoesfishinn.timetablespbu.presentation.main.mappers.group

import org.alexgoesfishinn.timetablespbu.domain.entities.Group
import org.alexgoesfishinn.timetablespbu.presentation.main.model.GroupItem
import javax.inject.Inject

class GroupToUiMapper @Inject constructor(): (Group) -> GroupItem {
    override fun invoke(p1: Group): GroupItem {
        return GroupItem(
            groupId = p1.groupId,
            groupName = p1.groupName,
            groupProfiles = p1.groupProfiles,
            groupStudyForm = p1.groupStudyForm
        )
    }
}