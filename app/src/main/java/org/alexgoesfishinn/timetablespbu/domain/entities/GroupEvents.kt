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

