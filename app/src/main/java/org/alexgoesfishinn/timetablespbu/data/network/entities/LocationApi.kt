package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LocationApi (
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
    val educators: List<EducatorApi>
)