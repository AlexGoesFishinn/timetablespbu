package org.alexgoesfishinn.timetablespbu.presentation.main.model

/**
 * @author a.bylev
 */
data class EventItem(
    val kindCode: Int,
    val start: String,
    val end: String,
    val subject: String,
    val timeIntervalString: String,
    val dateTimeIntervalString: String,
    val displayDateTimeIntervalString: String,
    val locationDisplayText: String,
    val educatorDisplayText: String,
    val hasEducators: Boolean,
    val isCancelled: Boolean,
    val contingentUnitName: String,
    val divisionAndCourse: String,
    val isAssigned: Boolean,
    val timeWasChanged: Boolean,
    val locationWasChanged: Boolean,
    val educatorsWereReassigned: Boolean,
    val electiveDisciplineCount: Int,
    val isElective: Boolean,
    val hasTheSameTimeAsPreviousItem: Boolean,
    val contingentUnitsDisplayTest: String?,
    val isStudy: Boolean,
    val allDay: Boolean,
    val withinTheSameDay: Boolean,
    val locations: List<LocationItem>
)
