package com.moya.funch.datasource.remote

import com.moya.funch.network.dto.response.subway_station.SubwayStationsResponse

fun interface RemoteSubwayDataSource {

    suspend fun fetchSubwayStations(subwayStation: String): Result<List<SubwayStationsResponse>>

}
