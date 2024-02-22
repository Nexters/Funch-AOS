package com.moya.funch.datasource.remote

import com.moya.funch.network.dto.response.match.MatchingResponse

interface RemoteMatchDataSource {
    suspend fun matchProfile(targetCode: String): Result<MatchingResponse>

    suspend fun canMatchProfile(targetCode: String): Result<Boolean>
}
