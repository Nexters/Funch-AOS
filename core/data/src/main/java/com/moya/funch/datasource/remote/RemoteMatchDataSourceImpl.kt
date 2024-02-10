package com.moya.funch.datasource.remote

import com.moya.funch.datastore.UserDataStore
import com.moya.funch.network.dto.request.MatchingRequest
import com.moya.funch.network.dto.response.match.MatchingResponse
import com.moya.funch.network.service.MatchingService
import javax.inject.Inject

class RemoteMatchDataSourceImpl @Inject constructor(
    private val matchingService: MatchingService,
    private val dataStore: UserDataStore
) : RemoteMatchDataSource {

    override suspend fun matchProfile(targetCode: String): Result<MatchingResponse> {
        return runCatching {
            matchingService.matchProfile(
                MatchingRequest(
                    targetCode,
                    dataStore.userId
                )
            )
        }.map { it.data }
    }
}
