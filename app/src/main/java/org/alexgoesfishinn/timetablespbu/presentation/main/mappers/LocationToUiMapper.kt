package org.alexgoesfishinn.timetablespbu.presentation.main.mappers

import org.alexgoesfishinn.timetablespbu.domain.entities.Location
import org.alexgoesfishinn.timetablespbu.presentation.main.model.LocationItem
import javax.inject.Inject

class LocationToUiMapper @Inject constructor(
    private val educatorToUiMapper: EducatorToUiMapper
): (Location) -> LocationItem {
    override fun invoke(p1: Location): LocationItem {
        return LocationItem(
            displayName = p1.displayName,
            educatorsDisplayText = p1.educatorsDisplayText,
            hasEducators = p1.hasEducators,
            hasGeographicCoordinates = p1.hasGeographicCoordinates,
            latitude = p1.latitude,
            longitude = p1.longitude,
            latitudeString = p1.latitudeString,
            longitudeString = p1.longitudeString,
            isEmpty = p1.isEmpty,
            educators = p1.educators.map { educatorToUiMapper.invoke(it) }
        )
    }
}