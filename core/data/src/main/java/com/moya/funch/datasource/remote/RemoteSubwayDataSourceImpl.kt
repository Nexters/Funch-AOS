package com.moya.funch.datasource.remote

import com.moya.funch.network.dto.response.subway_station.SubwayStationsResponse
import com.moya.funch.network.service.SubwayService
import javax.inject.Inject

class RemoteSubwayDataSourceImpl @Inject constructor(
    private val subwayStationService: SubwayService
) : RemoteSubwayDataSource {

    override suspend fun fetchSubwayStations(subwayStation: String): Result<List<SubwayStationsResponse>> {
        return runCatching {
            subwayStationService.findSubwayStations(subwayStation = subwayStation)
        }.mapCatching { response -> response.data }
    }

}
