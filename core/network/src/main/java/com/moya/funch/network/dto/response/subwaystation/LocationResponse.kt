package com.moya.funch.network.dto.response.subwaystation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("latitude")
    val latitude: String = "",
    @SerialName("longitude")
    val longitude: String = ""
)
