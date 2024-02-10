package com.moya.funch.datasource.local

import com.moya.funch.datastore.UserDataStore
import com.moya.funch.model.ProfileModel
import javax.inject.Inject
import timber.log.Timber

class LocalUserDataSourceImpl @Inject constructor(
    private val userDataStore: UserDataStore
) : LocalUserDataSource {

    override suspend fun fetchUserProfile(): Result<ProfileModel> {
//        userDataStore.clear() 여기 부분 주석 해제하고 한 번 빌드돌리면 데이터가 초기화됩니다!!
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
}
