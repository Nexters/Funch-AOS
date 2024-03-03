package com.moya.funch.repository

import com.moya.funch.datasource.local.LocalUserDataSource
import com.moya.funch.entity.Mbti
import javax.inject.Inject

class MbtiCollectionRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource
) : MbtiCollectionRepository {
    override suspend fun addMbtiCollection(): Result<Unit> {
        // @Gun Hyung Todo : 추후 데이터 레이어 리팩토링 이후 작업 진행
        return Result.success(Unit)
    }

    override suspend fun loadMbtiCollection(): Result<List<Mbti>> {
        return localUserDataSource.fetchUserMbtiCollection().mapCatching { mbtiList ->
            mbtiList.map { mbti -> Mbti.valueOf(mbti) }
        }
    }
}
