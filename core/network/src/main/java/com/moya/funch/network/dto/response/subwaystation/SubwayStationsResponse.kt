package com.moya.funch.network.dto.response.subwaystation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubwayStationsResponse(
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("lines")
    val lines: List<String> = listOf(),
    @SerialName("location")
    val location: LocationResponse = LocationResponse()
)
