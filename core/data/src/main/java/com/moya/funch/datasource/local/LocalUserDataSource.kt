package com.moya.funch.datasource.local

import com.moya.funch.datasource.UserDataSource
import com.moya.funch.model.ProfileModel

interface LocalUserDataSource : UserDataSource {
    suspend fun saveUserProfile(userModel: ProfileModel): Result<Unit>
}
