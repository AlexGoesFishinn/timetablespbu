package org.alexgoesfishinn.timetablespbu.domain.entities

/**
 * @author a.bylev
 */

data class Location (
    val isEmpty: Boolean,
    val displayName: String,
    val hasGeographicCoordinates: Boolean,
    val latitude: Double,
    val longitude: Double,
    val latitudeString: String,
    val longitudeString: String,
    val educatorsDisplayText: String,
    val hasEducators: String,
    val educators: List<Educator>
)
