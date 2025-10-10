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

/*
{"IsEmpty":false,
            "DisplayName":"6-я линия В.О., д. 15, лит. A, 211",
            "HasGeographicCoordinates":true,
            "Latitude":59.94088,
            "Longitude":30.28176,
            "LatitudeValue":"59.94088",
            "LongitudeValue":"30.28176",
            "EducatorsDisplayText":"Захарова Е. В., преподаватель",
            "HasEducators":true,
            "EducatorIds":
                [{"Item1":-1,
                "Item2":"Захарова Е. В., преподаватель"}]}
 */