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
                    userId = DUMMY_ID,
                    targetCode = targetCode
                )
            )
            // @murjune TODO change - dataStore.userId
        }.mapCatching { it.data }
    }

    companion object {
        private const val DUMMY_ID = "65c9d597ffc89209ea09ce24"
    }
}
