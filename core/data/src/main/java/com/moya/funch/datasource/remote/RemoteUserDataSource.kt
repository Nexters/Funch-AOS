package com.moya.funch.datasource.remote

import com.moya.funch.datasource.UserDataSource
import com.moya.funch.model.ProfileModel

interface RemoteUserDataSource : UserDataSource {
    suspend fun createUserProfile(userModel: ProfileModel): Result<ProfileModel>
}
