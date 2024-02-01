package com.moya.funch.network.dto.response.match

import com.moya.funch.network.dto.response.profile.ProfileResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchingResponse(
    @SerialName("profile")
    val profile: ProfileResponse = ProfileResponse(),
    @SerialName("similarity")
    val similarity: Int = 0,
    @SerialName("chemistryInfos")
    val chemistryInfos: List<ChemistryResponse> = listOf(),
    @SerialName("recommendInfos")
    val recommends: List<RecommendResponse> = listOf(),
    @SerialName("subwayInfos")
    val subways: List<SubwayResponse> = listOf(),
)
