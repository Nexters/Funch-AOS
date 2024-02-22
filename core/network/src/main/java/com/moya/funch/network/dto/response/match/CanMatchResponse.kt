package com.moya.funch.network.dto.response.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CanMatchResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String = "",
    @SerialName("code")
    val code: Int = 0,
    @SerialName("data")
    val data: Unit? = null
)
