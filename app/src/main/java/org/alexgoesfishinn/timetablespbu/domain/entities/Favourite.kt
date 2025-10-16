package org.alexgoesfishinn.timetablespbu.domain.entities



data class Favourite(
    val oid: Long = Long.MIN_VALUE,
    val displayName: String,
    val id: Long
)
