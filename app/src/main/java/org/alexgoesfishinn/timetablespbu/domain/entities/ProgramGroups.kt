package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProgramGroups (
    @SerializedName("Id")
    val programId: Long,
    @SerializedName("Groups")
    val groups: List<Group>
)