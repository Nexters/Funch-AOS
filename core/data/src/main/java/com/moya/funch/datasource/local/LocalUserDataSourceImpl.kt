package com.moya.funch.datasource.local

import com.moya.funch.datastore.UserDataStore
import com.moya.funch.entity.Mbti
import com.moya.funch.model.ProfileModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : LocalUserDataSource {

    override suspend fun fetchUserProfile(): Result<ProfileModel> {
        if (userDataStore.hasUserId()) {
            return Result.success(
                ProfileModel(
                    userCode = userDataStore.userCode,
                    userId = userDataStore.userId,
                    name = userDataStore.userName,
                    jobGroup = userDataStore.jobGroup,
                    bloodType = userDataStore.bloodType,
                    clubs = userDataStore.clubs,
                    subwayName = userDataStore.subwayName,
                    subwayLines = userDataStore.subwayLines,
                    mbti = userDataStore.mbti
                )
            )
        }
        Timber.e("cannot find User Information")
        return Result.failure(IllegalArgumentException("cannot find User Information"))
    }

    override suspend fun saveUserProfile(profile: ProfileModel): Result<Unit> {
        return runCatching {
            userDataStore.clear()
            profile.run {
                userDataStore.userCode = userCode
                userDataStore.userId = userId
                userDataStore.userName = name
                userDataStore.jobGroup = jobGroup
                userDataStore.bloodType = bloodType
                userDataStore.clubs = clubs
                userDataStore.subwayName = subwayName
                userDataStore.subwayLines = subwayLines
                userDataStore.mbti = mbti
            }
        }
    }

    override suspend fun fetchUserMbtiCollection(): Flow<List<Mbti>> = flow {
        emit(
            value = userDataStore.mbtiCollection.map { mbti ->
                Mbti.valueOf(mbti)
            }
        )
    }.catch {
        emit(emptyList())
        Timber.e(it.message)
    }
}
