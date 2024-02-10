package com.moya.funch.mapper

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.match.Chemistry
import com.moya.funch.entity.match.Matching
import com.moya.funch.entity.match.Recommend
import com.moya.funch.entity.profile.Profile
import com.moya.funch.network.dto.response.match.ChemistryResponse
import com.moya.funch.network.dto.response.match.MatchingResponse
import com.moya.funch.network.dto.response.match.ProfileResponse
import com.moya.funch.network.dto.response.match.RecommendResponse
import com.moya.funch.network.dto.response.match.SubwayResponse

fun MatchingResponse.toDomain(): Matching {
    return Matching(
        profile = profile.toDomain(),
        similarity = similarity,
        chemistrys = chemistryInfos.map { it.toDomain() },
        recommends = recommends.map { it.toDomain() },
        subways = subways.map { it.toDomain() }
    )
}

private fun ProfileResponse.toDomain(): Profile {
    return Profile(
        name = name,
        job = Job.of(jobGroup),
        clubs = clubs.map { Club.of(it) },
        mbti = Mbti.valueOf(mbti),
        blood = Blood.valueOf(bloodType),
        subways = subwayInfos.map { SubwayStation(name = it) }
    )
}

private fun ChemistryResponse.toDomain(): Chemistry {
    return Chemistry(
        title = title,
        description = description
    )
}

private fun RecommendResponse.toDomain(): Recommend {
    return Recommend(
        title = title
    )
}

private fun SubwayResponse.toDomain(): SubwayStation {
    return SubwayStation(name = name, lines = lines.map { SubwayLine.valueOf(it) })
}
