package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @author a.bylev
 */
@Serializable
data class Division(
    @SerializedName("Oid")
    val oid: String,
    @SerializedName("Alias")
    val alias: String,
    @SerializedName("Name")
    val name: String
)