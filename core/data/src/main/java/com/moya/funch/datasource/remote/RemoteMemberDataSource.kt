package com.moya.funch.datasource.remote

import com.moya.funch.model.ProfileModel

fun interface RemoteMemberDataSource {

    suspend fun fetchMemberProfile(id: String): Result<ProfileModel>
}
