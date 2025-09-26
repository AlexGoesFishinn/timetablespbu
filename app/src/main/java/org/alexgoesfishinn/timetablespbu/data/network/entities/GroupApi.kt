package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GroupApi(
    @SerializedName("StudentGroupId")
    val groupId: Long,
    @SerializedName("StudentGroupName")
    val groupName: String,
    @SerializedName("StudentGroupStudyForm")
    val groupStudyForm: String,
    @SerializedName("StudentGroupProfiles")
    val groupProfiles: String
)
