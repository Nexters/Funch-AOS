package com.moya.funch.network.dto.response.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendResponse(
    @SerialName("title")
    val title: String = ""
)
