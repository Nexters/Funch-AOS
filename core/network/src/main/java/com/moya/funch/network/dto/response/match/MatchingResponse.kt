package com.moya.funch.network.dto.response.match

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
    @SerialName("matchedInfos")
    val matchInfos: List<MatchInfoResponse> = listOf(),
    @SerialName("subwayChemistryInfo")
    val subwayChemistry: ChemistryResponse? = null
)
