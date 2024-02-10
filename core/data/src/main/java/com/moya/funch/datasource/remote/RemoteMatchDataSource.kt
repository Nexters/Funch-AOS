package com.moya.funch.datasource.remote

import com.moya.funch.network.dto.response.match.MatchingResponse

fun interface RemoteMatchDataSource {
    suspend fun matchProfile(targetCode: String): Result<MatchingResponse>
}
