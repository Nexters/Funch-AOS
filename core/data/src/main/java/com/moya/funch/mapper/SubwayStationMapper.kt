package com.moya.funch.mapper

import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation
import com.moya.funch.network.dto.response.subwaystation.SubwayStationsResponse

fun SubwayStationsResponse.toDomain() = SubwayStation(
    name = name,
    lines = lines.map { SubwayLine.valueOf(it) }
)
