package com.moya.funch.network.dto.response.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChemistryResponse(
    @SerialName("title")
    val title: String = "",
    @SerialName("description")
    val description: String = ""
)
