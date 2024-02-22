package com.moya.funch.repository

import com.moya.funch.entity.match.Matching

interface MatchingRepository {
    suspend fun matchProfile(targetCode: String): Result<Matching>

    suspend fun canMatchProfile(targetCode: String): Result<Unit>
}
