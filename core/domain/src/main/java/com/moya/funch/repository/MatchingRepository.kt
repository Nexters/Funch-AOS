package com.moya.funch.repository

import com.moya.funch.entity.match.MatchingResult

fun interface MatchingRepository {

    suspend fun matchProfile(
        userId: String,
        targetCode: String,
    ): MatchingResult
}
