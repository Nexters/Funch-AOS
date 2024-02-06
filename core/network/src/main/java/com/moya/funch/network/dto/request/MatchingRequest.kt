package com.moya.funch.network.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchingRequest(
    @SerialName("requestMemberId") val userId: String,
    @SerialName("targetMemberCode") val targetCode: String
)
