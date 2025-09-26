package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class ProgramGroupsApi (
    @SerializedName("Id")
    val programId: Long,
    @SerializedName("Groups")
    val groups: List<GroupApi>
)