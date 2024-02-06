package com.moya.funch.repository

import com.moya.funch.entity.match.Matching

fun interface MatchingRepository {
    suspend fun matchProfile(
        userId: String,
        targetCode: String,
    ): Matching
}
