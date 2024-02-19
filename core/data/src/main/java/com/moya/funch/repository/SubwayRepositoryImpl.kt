package com.moya.funch.repository

import com.moya.funch.datasource.remote.RemoteSubwayDataSource
import com.moya.funch.entity.SubwayStation
import com.moya.funch.mapper.toDomain
import javax.inject.Inject

class SubwayRepositoryImpl @Inject constructor(
    private val remoteSubwayDataSource: RemoteSubwayDataSource
) : SubwayRepository {

    override suspend fun fetchSubwayStations(subwayStation: String): Result<List<SubwayStation>> {
        return remoteSubwayDataSource.fetchSubwayStations(subwayStation = subwayStation)
            .mapCatching { response ->
                response.map { it.toDomain() }
            }
    }
}
