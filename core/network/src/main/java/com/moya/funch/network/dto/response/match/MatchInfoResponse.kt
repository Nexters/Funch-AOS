package com.moya.funch.network.dto.response.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchInfoResponse(
    @SerialName("title")
    val title: String = ""
)
