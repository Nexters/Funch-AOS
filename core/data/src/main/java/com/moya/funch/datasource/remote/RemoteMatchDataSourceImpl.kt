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
                    userId = dataStore.userId,
                    targetCode = targetCode
                )
            )
        }.mapCatching {
            dataStore.mbtiCollection = dataStore.mbtiCollection.toMutableSet().apply {
                add(it.data.profile.mbti)
            }.toSet()
            it.data
        }
    }

    override suspend fun canMatchProfile(targetCode: String): Result<Boolean> {
        return runCatching { matchingService.canMatchProfile(targetCode).code == SUCCESS_CODE }
    }

    companion object {
        private const val SUCCESS_CODE = 1000 // @murjune TODO 추후 : DTO 전면 개편 시 Enum 으로 가져갈지 논의
        private const val FAILURE_CODE = 4001
    }
}
