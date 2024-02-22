package com.moya.funch.network.dto.response.member

import com.moya.funch.network.dto.response.match.SubwayResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberResponse(
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("bloodType")
    val bloodType: String = "",
    @SerialName("jobGroup")
    val jobGroup: String = "",
    @SerialName("clubs")
    val clubs: List<String> = listOf(),
    @SerialName("mbti")
    val mbti: String = "",
    @SerialName("memberCode")
    val memberCode: String = "",
    @SerialName("subwayInfos")
    val subwayInfos: List<SubwayResponse> = listOf(),
    @SerialName("viewCount")
    val viewCount: Int = 0
)
