package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
/**
 * @author a.bylev
 */
@Serializable
data class GroupEventsApi (
    @SerializedName("StudentGroupId")
    val groupId: Long,
    @SerializedName("StudentGroupDisplayName")
    val groupName: String,
    @SerializedName("TimeTableDisplayName")
    val timeTableDisplayName: String,
    @SerializedName("PreviousWeekMonday")
    val previousWeekMonday: String,
    @SerializedName("NextWeekMonday")
    val nextWeekMonday: String,
    @SerializedName("IsPreviousWeekReferenceAvailable")
    val isPreviousWeekReferenceAvailable: Boolean,
    @SerializedName("IsNextWeekReferenceAvailable")
    val isNextWeekReferenceAvailable: Boolean,
    @SerializedName("IsCurrentWeekReferenceAvailable")
    val isCurrentWeekReferenceAvailable: Boolean,
    @SerializedName("WeekDisplayText")
    val weekDisplayText: String,
    @SerializedName("WeekMonday")
    val weekMonday: String,
    @SerializedName("Days")
    val days: List<DayApi>
)