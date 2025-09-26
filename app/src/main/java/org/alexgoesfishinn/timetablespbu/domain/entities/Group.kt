package org.alexgoesfishinn.timetablespbu.domain.entities

/**
 * @author a.bylev
 */

data class Group (
    val groupId: Long,
    val groupName: String,
    val groupStudyForm: String,
    val groupProfiles: String
)