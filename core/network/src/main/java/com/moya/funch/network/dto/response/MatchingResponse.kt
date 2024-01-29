package com.moya.funch.network.dto.response

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
) {
    @Serializable
    data class ProfileResponse(
        @SerialName("name")
        val name: String = "",
        @SerialName("jobGroup")
        val jobGroup: String = "",
        @SerialName("clubs")
        val clubs: List<String> = listOf(),
        @SerialName("mbti")
        val mbti: String = "",
        @SerialName("constellation")
        val constellation: String = "",
        @SerialName("subwayNames")
        val subwayNames: List<String> = listOf(),
    )

    @Serializable
    data class ChemistryResponse(
        @SerialName("title")
        val title: String = "",
        @SerialName("description")
        val description: String = "",
    )

    @Serializable
    data class RecommendResponse(
        @SerialName("title")
        val title: String = "",
    )

    @Serializable
    data class SubwayResponse(
        @SerialName("lines")
        val lines: List<String> = listOf(),
        @SerialName("name")
        val name: String = "",
    )
}
