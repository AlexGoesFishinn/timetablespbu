package org.alexgoesfishinn.timetablespbu.domain.entities

/**
 * @author a.bylev
 */

data class Event(
    val kindCode: Int,
    val start: String,
    val end: String,
    val subject: String,
    val timeIntervalString: String,
    val dateTimeIntervalString: String,
    val displayDateTimeIntervalString: String,
    val locationDisplayText: String,
    val educatorDisplayText: String,
    val hasEducators: Boolean,
    val isCancelled: Boolean,
    val contingentUnitName: String,
    val divisionAndCourse: String,
    val isAssigned: Boolean,
    val timeWasChanged: Boolean,
    val locationWasChanged: Boolean,
    val educatorsWereReassigned: Boolean,
    val electiveDisciplineCount: Int,
    val isElective: Boolean,
    val hasTheSameTimeAsPreviousItem: Boolean,
    val contingentUnitsDisplayTest: String?,
    val isStudy: Boolean,
    val allDay: Boolean,
    val withinTheSameDay: Boolean,
    val locations: List<Location>
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