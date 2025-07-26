package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @author a.bylev
 */
@Serializable
data class Divisions (
    val divisions: List<Division>
)