package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Program (
    @SerializedName("StudyProgramId")
    val programId: Long,
    @SerializedName("YearName")
    val yearName: String,
    @SerializedName("YearNumber")
    val yearNumber: Int,
    @SerializedName("IsEmpty")
    val isEmpty: Boolean,
    @SerializedName("PublicDivisionAlias")
    val divisionAlias: String
)