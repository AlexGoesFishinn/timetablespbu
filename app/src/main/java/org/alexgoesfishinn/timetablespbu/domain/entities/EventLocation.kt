package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EventLocation (
    @SerializedName("IsEmpty")
    val isEmpty: Boolean,
    @SerializedName("DisplayName")
    val displayName: String,
    @SerializedName("HasGeographicCoordinates")
    val hasGeographicCoordinates: Boolean,
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double,
    @SerializedName("LatitudeValue")
    val latitudeString: String,
    @SerializedName("LongitudeValue")
    val longitudeString: String,
    @SerializedName("EducatorsDisplayText")
    val educatorsDisplayText: String,
    @SerializedName("HasEducators")
    val hasEducators: String,
    @SerializedName("EducatorIds")
    val educatorIds: List<EducatorId>
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