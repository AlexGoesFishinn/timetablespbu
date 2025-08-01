package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProgramCombination (
    @SerializedName("Name")
    val programName: String,
    @SerializedName("NameEnglish")
    val programNameEng: String,
    @SerializedName("AdmissionYears")
    val programs: List<Program>
)