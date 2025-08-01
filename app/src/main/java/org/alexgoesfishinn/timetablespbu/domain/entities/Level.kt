package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Level (
    @SerializedName("StudyLevelName")
    val levelName: String,
    @SerializedName("StudyLevelNameEnglish")
    val levelNameEng: String,
    @SerializedName("HasCourse6")
    val hasCourse6: Boolean,
    @SerializedName("StudyProgramCombinations")
    val programCombinations: List<ProgramCombination>

)

