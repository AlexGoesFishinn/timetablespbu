package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
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
    val eventLocations: List<EventLocation>,
    @SerializedName("EducatorIds")
    val educatorIds: List<EducatorId>
    )

/*
{"StudyEventsTimeTableKindCode":0,
        "Start":"2025-06-23T12:00:00",
        "End":"2025-06-23T13:30:00",
        "Subject":"Оперный класс, консультация групповая",
        "TimeIntervalString":"12:00–13:30",
        "DateWithTimeIntervalString":"23 июня 12:00–13:30",
        "DisplayDateAndTimeIntervalString":"23 июня 12:00–13:30",
        "LocationsDisplayText":"6-я линия В.О., д. 15, лит. A, 211",
        "EducatorsDisplayText":"Захарова Е. В., преподаватель",
        "HasEducators":true,
        "IsCancelled":false,
        "ContingentUnitName":"",
        "DivisionAndCourse":"",
        "IsAssigned":false,
        "TimeWasChanged":false,
        "LocationsWereChanged":false,
        "EducatorsWereReassigned":false,
        "ElectiveDisciplinesCount":1,
        "IsElective":false,
        "HasTheSameTimeAsPreviousItem":false,
        "ContingentUnitsDisplayTest":null,
        "IsStudy":false,
        "AllDay":false,
        "WithinTheSameDay":false,
        "EventLocations":
            [{"IsEmpty":false,
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
                "Item2":"Захарова Е. В., преподаватель"}]}],
        "EducatorIds":
            [{"Item1":-1,"Item2":"Захарова Е. В., преподаватель"}]}
 */