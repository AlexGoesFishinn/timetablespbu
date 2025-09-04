package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
/**
 * @author a.bylev
 */
@Serializable
data class Group (
    @SerializedName("StudentGroupId")
    val groupId: Long,
    @SerializedName("StudentGroupName")
    val groupName: String,
    @SerializedName("StudentGroupStudyForm")
    val groupStudyForm: String,
    @SerializedName("StudentGroupProfiles")
    val groupProfiles: String
)