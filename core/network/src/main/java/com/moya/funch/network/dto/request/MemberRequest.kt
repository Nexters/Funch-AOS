package com.moya.funch.network.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberRequest(
    @SerialName("name")
    val name: String,
    @SerialName("jobGroup")
    val jobGroup: String,
    @SerialName("clubs")
    val clubs: List<String>,
    @SerialName("bloodType")
    val bloodType: String,
    @SerialName("subwayStations")
    val subwayStations: List<String>,
    @SerialName("mbti")
    val mbti: String,
    @SerialName("deviceNumber")
    val deviceNumber: String
)
