package org.alexgoesfishinn.timetablespbu.data.network.mappers.location

import org.alexgoesfishinn.timetablespbu.data.network.entities.LocationApi
import org.alexgoesfishinn.timetablespbu.data.network.mappers.educator.EducatorApiToDbMapper
import org.alexgoesfishinn.timetablespbu.data.storage.entities.EducatorDb
import org.alexgoesfishinn.timetablespbu.data.storage.entities.LocationDb
import javax.inject.Inject

class LocationApiToDbMapper @Inject constructor(
    private val educatorApiToDbMapper: EducatorApiToDbMapper
): (LocationApi) -> LocationDb {
    override fun invoke(p1: LocationApi): LocationDb {
        val educators: MutableList<EducatorDb> = mutableListOf()
        p1.educators.forEach {
            educators.add(educatorApiToDbMapper.invoke(it))
        }
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