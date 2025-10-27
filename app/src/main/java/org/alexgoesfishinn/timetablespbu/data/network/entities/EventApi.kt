package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
/**
 * @author a.bylev
 */
@Serializable
data class EventApi (
    @SerializedName("StudyEventsTimeTableKindCode")
    val kindCode: Int,
    @SerializedName("Start")
    val start: String,
    @SerializedName("End")
    val end: String,
    @SerializedName("Subject")
    val subject: String,
    @SerializedName("TimeIntervalString")
    val timeIntervalString: String,
    @SerializedName("DateWithTimeIntervalString")
    val dateTimeIntervalString: String,
    @SerializedName("DisplayDateAndTimeIntervalString")
    val displayDateTimeIntervalString: String,
    @SerializedName("LocationsDisplayText")
    val locationDisplayText: String,
    @SerializedName("EducatorsDisplayText")
    val educatorDisplayText: String,
    @SerializedName("HasEducators")
    val hasEducators: Boolean,
    @SerializedName("IsCancelled")
    val isCancelled: Boolean,
    @SerializedName("ContingentUnitName")
    val contingentUnitName: String,
    @SerializedName("DivisionAndCourse")
    val divisionAndCourse: String,
    @SerializedName("IsAssigned")
    val isAssigned: Boolean,
    @SerializedName("TimeWasChanged")
    val timeWasChanged: Boolean,
    @SerializedName("LocationsWereChanged")
    val locationWasChanged: Boolean,
    @SerializedName("EducatorsWereReassigned")
    val educatorsWereReassigned: Boolean,
    @SerializedName("ElectiveDisciplinesCount")
    val electiveDisciplineCount: Int,
    @SerializedName("IsElective")
    val isElective: Boolean,
    @SerializedName("HasTheSameTimeAsPreviousItem")
    val hasTheSameTimeAsPreviousItem: Boolean,
    @SerializedName("ContingentUnitsDisplayTest")
    val contingentUnitsDisplayTest: String?,
    @SerializedName("IsStudy")
    val isStudy: Boolean,
    @SerializedName("AllDay")
    val allDay: Boolean,
    @SerializedName("WithinTheSameDay")
    val withinTheSameDay: Boolean,
    @SerializedName("EventLocations")
    val locations: List<LocationApi>,
    @SerializedName("EducatorIds")
    val educators: List<EducatorApi>
)