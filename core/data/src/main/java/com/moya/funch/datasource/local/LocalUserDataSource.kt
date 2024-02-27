package com.moya.funch.datasource.local

import com.moya.funch.datasource.UserDataSource
import com.moya.funch.entity.Mbti
import com.moya.funch.model.ProfileModel
import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource : UserDataSource {
    suspend fun saveUserProfile(userModel: ProfileModel): Result<Unit>

    suspend fun fetchUserMbtiCollection(): Flow<List<Mbti>>
}
