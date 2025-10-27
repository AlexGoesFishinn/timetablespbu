package org.alexgoesfishinn.timetablespbu.data.storage.mappers.location

import org.alexgoesfishinn.timetablespbu.data.storage.entities.LocationDb
import org.alexgoesfishinn.timetablespbu.data.storage.mappers.educator.EducatorDbToDomainMapper
import org.alexgoesfishinn.timetablespbu.domain.entities.Location
import javax.inject.Inject
/**
 * @author a.bylev
 */
class LocationDbToDomainMapper @Inject constructor(
    private val educatorDbToDomainMapper: EducatorDbToDomainMapper
): (LocationDb) -> Location {
    override fun invoke(p1: LocationDb): Location {
        val educators = p1.educators.map { educatorDbToDomainMapper.invoke(it) }
        val location = Location(
            displayName = p1.displayName,
            educatorsDisplayText = p1.educatorsDisplayText,
            hasEducators = p1.hasEducators,
            hasGeographicCoordinates = p1.hasGeographicCoordinates,
            isEmpty = p1.isEmpty,
            latitude = p1.latitude,
            longitude = p1.longitude,
            latitudeString = p1.latitudeString,
            longitudeString = p1.longitudeString,
            educators = educators
        )
        return location
    }
}