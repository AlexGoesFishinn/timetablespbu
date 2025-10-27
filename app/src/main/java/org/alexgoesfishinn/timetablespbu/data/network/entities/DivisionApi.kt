package org.alexgoesfishinn.timetablespbu.data.network.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
/**
 * @author a.bylev
 */
@Serializable
data class DivisionApi (
    @SerializedName("Oid")
    val oid: String,
    @SerializedName("Alias")
    val alias: String,
    @SerializedName("Name")
    val name: String
)