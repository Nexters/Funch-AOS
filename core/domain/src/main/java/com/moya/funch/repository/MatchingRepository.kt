package com.moya.funch.repository

import com.moya.funch.entity.match.Matching

fun interface MatchingRepository {
    suspend fun matchProfile(targetCode: String): Result<Matching>
}
