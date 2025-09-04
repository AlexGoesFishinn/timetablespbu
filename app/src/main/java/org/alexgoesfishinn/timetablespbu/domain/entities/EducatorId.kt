package org.alexgoesfishinn.timetablespbu.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
/**
 * @author a.bylev
 */
@Serializable
data class EducatorId (
    @SerializedName("Item1")
    val item1: Int,
    @SerializedName("Item2")
    val item2: String
)
/*
{"Item1":-1,
                "Item2":"Захарова Е. В., преподаватель"}
 */