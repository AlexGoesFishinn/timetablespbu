package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EducatorApi (
    @SerializedName("Item1")
    val item1: Int,
    @SerializedName("Item2")
    val name: String
)