package com.moya.funch.network.dto.response.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubwayResponse(
    @SerialName("lines")
    val lines: List<String> = listOf(),
    @SerialName("name")
    val name: String = ""
)
