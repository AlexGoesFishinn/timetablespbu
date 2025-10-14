package org.alexgoesfishinn.timetablespbu.domain.entities

/**
 * @author a.bylev
 */

data class Day (
    val id: Long,
    val dateString:String,
    val name: String,
    val events: List<Event>
)