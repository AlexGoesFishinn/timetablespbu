package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProgramCombinationApi (
    @SerializedName("Name")
    val programName: String,
    @SerializedName("NameEnglish")
    val programNameEng: String,
    @SerializedName("AdmissionYears")
    val programs: List<ProgramApi>
)