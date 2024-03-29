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
            return profileResult.mapCatching { it.toDomain() }
        }

        return remoteUserDataSource.fetchUserProfile().onSuccess {
            localUserDataSource.saveUserProfile(it)
        }.mapCatching { it.toDomain() }
    }

    override suspend fun createUserProfile(profile: Profile): Result<Profile> {
        return remoteUserDataSource.createUserProfile(profile.toModel())
            .onSuccess {
                localUserDataSource.saveUserProfile(it)
            }.mapCatching { it.toDomain() }
    }

    override suspend fun fetchUserViewCount(): Result<Int> {
        return remoteUserDataSource.fetchUserProfile().mapCatching {
            it.viewCount
        }
    }

    override suspend fun fetchMemberProfile(id: String): Result<Profile> {
        return remoteMemberDataSource.fetchMemberProfile(id).mapCatching {
            it.toDomain()
        }
    }

    override suspend fun deleteUserProfile(): Result<String> {
        return remoteUserDataSource.deleteUserProfile()
    }
}
