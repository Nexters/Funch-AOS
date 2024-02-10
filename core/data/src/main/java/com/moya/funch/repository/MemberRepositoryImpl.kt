package com.moya.funch.repository

import com.moya.funch.datasource.local.LocalUserDataSource
import com.moya.funch.datasource.remote.RemoteMemberDataSource
import com.moya.funch.datasource.remote.RemoteUserDataSource
import com.moya.funch.entity.profile.Profile
import com.moya.funch.model.toModel
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localUserDataSource: LocalUserDataSource,
    private val remoteMemberDataSource: RemoteMemberDataSource
) : MemberRepository {
    override suspend fun fetchUserProfile(): Result<Profile> {
        val profileResult = localUserDataSource.fetchUserProfile()
        if (profileResult.isSuccess) {
            return profileResult.map { it.toDomain() }
        }
        return remoteUserDataSource.fetchUserProfile().onSuccess {
            localUserDataSource.saveUserProfile(it)
        }.map { it.toDomain() }
    }

    override suspend fun createUserProfile(profile: Profile): Result<Profile> {
        return remoteUserDataSource.createUserProfile(profile.toModel())
            .onSuccess {
                localUserDataSource.saveUserProfile(it)
            }.map { it.toDomain() }
    }

    override suspend fun fetchUserViewCount(): Result<Int> {
        return remoteUserDataSource.fetchUserProfile().map {
            it.viewCount
        }
    }

    override suspend fun fetchMemberProfile(id: String): Result<Profile> {
        return remoteMemberDataSource.fetchMemberProfile(id).map {
            it.toDomain()
        }
    }

}
