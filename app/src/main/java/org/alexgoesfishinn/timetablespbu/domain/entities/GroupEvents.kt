package org.alexgoesfishinn.timetablespbu.domain.entities


/**
 * @author a.bylev
 */

data class GroupEvents (
    val groupId: Long,
    val groupName: String,
    val timeTableDisplayName: String,
    val previousWeekMonday: String,
    val nextWeekMonday: String,
    val isPreviousWeekReferenceAvailable: Boolean,
    val isNextWeekReferenceAvailable: Boolean,
    val isCurrentWeekReferenceAvailable: Boolean,
    val weekDisplayText: String,
    val weekMonday: String,
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

