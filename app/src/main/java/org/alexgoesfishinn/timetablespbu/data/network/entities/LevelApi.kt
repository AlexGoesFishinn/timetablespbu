package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
/**
 * @author a.bylev
 */
@Serializable
data class LevelApi (
    @SerializedName("StudyLevelName")
    val levelName: String,
    @SerializedName("StudyLevelNameEnglish")
    val levelNameEng: String,
    @SerializedName("HasCourse6")
    val hasCourse6: Boolean,
    @SerializedName("StudyProgramCombinations")
    val programCombinations: List<ProgramCombinationApi>
)