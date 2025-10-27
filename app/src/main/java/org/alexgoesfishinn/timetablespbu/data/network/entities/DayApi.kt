package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
/**
 * @author a.bylev
 */
@Serializable
data class DayApi (
    @SerializedName("Day")
    val dateString:String,
    @SerializedName("DayString")
    val name: String,
    @SerializedName("DayStudyEvents")
    val events: List<EventApi>
)