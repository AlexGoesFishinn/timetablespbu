package org.alexgoesfishinn.timetablespbu.data.network.mappers.location

import org.alexgoesfishinn.timetablespbu.data.network.entities.LocationApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.educator.EducatorApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LocationDb
import javax.inject.Inject
/**
 * @author a.bylev
 */
class LocationApiToDbMapper @Inject constructor(
    private val educatorApiToDbMapper: EducatorApiToDbMapper
): (LocationApi) -> LocationDb {
    override fun invoke(p1: LocationApi): LocationDb {
        val educators = p1.educators.map { educatorApiToDbMapper.invoke(it) }

        val locationDb = LocationDb(
            displayName = p1.displayName,
            educatorsDisplayText = p1.educatorsDisplayText,
            hasEducators = p1.hasEducators,
            hasGeographicCoordinates = p1.hasGeographicCoordinates,
            isEmpty = p1.isEmpty,
            latitude = p1.latitude,
            longitude = p1.longitude,
            latitudeString = p1.latitudeString,
            longitudeString = p1.longitudeString
        )
        locationDb.educators = educators
        return locationDb
    }
}