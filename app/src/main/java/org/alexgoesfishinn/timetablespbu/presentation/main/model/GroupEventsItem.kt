package org.alexgoesfishinn.timetablespbu.presentation.main.model

/**
 * @author a.bylev
 */
data class GroupEventsItem(
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
    val days: List<DayItem>
)
