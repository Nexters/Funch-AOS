package com.moya.funch.repository

import com.moya.funch.entity.Mbti
import kotlinx.coroutines.flow.Flow

interface MbtiCollectionRepository {
    // @Gun Hyung Todo : 추후 데이터 레이어 리팩토링 이후 작업 진행
    suspend fun addMbtiCollection(): Result<Unit>

    suspend fun loadMbtiCollection(): Flow<List<Mbti>>
}
