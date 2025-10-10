package org.alexgoesfishinn.timetablespbu.presentation.main.model


data class DayItem(
    val dateString:String,
    val name: String,
    val events: List<EventItem>
)
