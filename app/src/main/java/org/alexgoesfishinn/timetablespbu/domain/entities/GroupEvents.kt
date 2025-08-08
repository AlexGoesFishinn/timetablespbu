package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GroupEvents (
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
    val days: List<Day>

)
/*
{"StudentGroupId":394871,
"StudentGroupDisplayName":"Группа 24.Б01-и",
"TimeTableDisplayName":"Все занятия",
"PreviousWeekMonday":"2025-07-28",
"NextWeekMonday":"2025-08-11",
"IsPreviousWeekReferenceAvailable":true,
"IsNextWeekReferenceAvailable":true,
"IsCurrentWeekReferenceAvailable":false,
"WeekDisplayText":"4 августа – 10 августа",
"WeekMonday":"2025-08-04",
"Days":[]}
*/

