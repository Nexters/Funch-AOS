package com.moya.funch.network.dto.response.match

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    @SerialName("bloodType")
    val bloodType: String = "",
    @SerialName("subwayNames")
    val subwayNames: List<String> = listOf()
)
