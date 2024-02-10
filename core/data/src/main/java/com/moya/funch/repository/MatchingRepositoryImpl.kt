package com.moya.funch.repository

import com.moya.funch.datasource.remote.RemoteMatchDataSource
import com.moya.funch.entity.match.Matching
import com.moya.funch.mapper.toDomain
import javax.inject.Inject

class MatchingRepositoryImpl @Inject constructor(
    private val remoteMatchDataSource: RemoteMatchDataSource
) : MatchingRepository {
    override suspend fun matchProfile(targetCode: String): Result<Matching> {
        return remoteMatchDataSource.matchProfile(targetCode).map { it.toDomain() }
    }
}
