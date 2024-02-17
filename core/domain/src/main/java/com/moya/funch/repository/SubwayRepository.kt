package com.moya.funch.repository

import com.moya.funch.entity.SubwayStation

interface SubwayRepository {

    suspend fun fetchSubwayStations(subwayStation: String): Result<List<SubwayStation>>

}
