package org.alexgoesfishinn.timetablespbu.data.network.mappers.programgroups

import org.alexgoesfishinn.timetablespbu.data.network.entities.ProgramGroupsApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.group.GroupApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.GroupDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.ProgramGroupsDb
import javax.inject.Inject

class ProgramGroupsApiToDbMapper @Inject constructor(
    private val groupApiToDbMapper: GroupApiToDbMapper
): (ProgramGroupsApi) -> ProgramGroupsDb {
    override fun invoke(p1: ProgramGroupsApi): ProgramGroupsDb {
        val groups = mutableListOf<GroupDb>()
        p1.groups.forEach { groups.add(groupApiToDbMapper.invoke(it)) }
        val programGroups = ProgramGroupsDb(
            programId = p1.programId
        )
        programGroups.groups = groups
        return programGroups
    }
}