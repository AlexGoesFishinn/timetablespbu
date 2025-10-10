package org.alexgoesfishinn.timetablespbu.presentation.main.model


data class LocationItem(
    val isEmpty: Boolean,
    val displayName: String,
    val hasGeographicCoordinates: Boolean,
    val latitude: Double,
    val longitude: Double,
    val latitudeString: String,
    val longitudeString: String,
    val educatorsDisplayText: String,
    val hasEducators: String,
    val educators: List<EducatorItem>
)
