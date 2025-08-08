package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Day (
    @SerializedName("Day")
    val dateString:String,
    @SerializedName("DayString")
    val name: String,
    @SerializedName("DayStudyEvents")
    val events: List<Event>
)