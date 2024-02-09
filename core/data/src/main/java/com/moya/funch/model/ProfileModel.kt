package com.moya.funch.model

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.profile.Profile
import com.moya.funch.network.dto.request.MemberRequest
import com.moya.funch.network.dto.response.match.SubwayResponse
import com.moya.funch.network.dto.response.member.MemberResponse

data class ProfileModel(
    val userCode: String,
    val userId: String,
    val name: String,
    val jobGroup: String,
    var bloodType: String,
    val clubs: Set<String>,
    val subwayName: String,
    val subwayLines: Set<String>,
    val mbti: String,
    val viewCount: Int = 0
) {
    fun toDomain(): Profile {
        return Profile(
            code = userCode,
            id = userId,
            name = name,
            job = Job.of(jobGroup),
            blood = Blood.valueOf(bloodType),
            clubs = clubs.map { club -> Club.of(club) },
            subways = listOf(
                SubwayStation(
                    name = subwayName,
                    lines = subwayLines.map { line -> SubwayLine.valueOf(line) }
                )
            ),
            mbti = Mbti.valueOf(mbti),
            viewCount = viewCount
        )
    }

    fun toResponse(): MemberResponse {
        return MemberResponse(
            memberCode = userCode,
            id = userId,
            name = name,
            jobGroup = jobGroup,
            bloodType = bloodType,
            clubs = clubs.toList(),
            subwayInfos = listOf(
                SubwayResponse(
                    name = subwayName,
                    lines = subwayLines.toList()
                )
            ),
            mbti = mbti,
            viewCount = viewCount
        )
    }

    fun toRequest(deviceNumber: String): MemberRequest {
        return MemberRequest(
            deviceNumber = deviceNumber,
            name = name,
            jobGroup = jobGroup,
            bloodType = bloodType,
            clubs = clubs.toList(),
            subwayStations = listOf(subwayName),
            mbti = mbti
        )
    }
}

fun Profile.toModel(): ProfileModel {
    return ProfileModel(
        userCode = code,
        userId = id,
        name = name,
        jobGroup = job.name,
        bloodType = blood.name,
        clubs = clubs.map { it.name }.toSet(),
        subwayName = subways.firstOrNull()?.name ?: "",
        subwayLines = subways.firstOrNull()?.lines?.map { it.name }?.toSet() ?: emptySet(),
        mbti = mbti.name,
        viewCount = viewCount
    )
}

fun MemberResponse.toModel(): ProfileModel {
    return ProfileModel(
        userCode = memberCode,
        userId = id,
        name = name,
        jobGroup = jobGroup,
        bloodType = bloodType,
        clubs = clubs.toSet(),
        subwayName = subwayInfos.firstOrNull()?.name ?: "",
        subwayLines = subwayInfos.firstOrNull()?.lines?.toSet() ?: emptySet(),
        mbti = mbti,
        viewCount = viewCount
    )
}
