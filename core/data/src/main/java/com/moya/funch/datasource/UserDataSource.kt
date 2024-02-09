package com.moya.funch.datasource

import com.moya.funch.model.ProfileModel

fun interface UserDataSource {
    suspend fun fetchUserProfile(): Result<ProfileModel>
}
