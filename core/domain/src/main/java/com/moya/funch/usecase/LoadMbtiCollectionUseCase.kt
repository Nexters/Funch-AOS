package com.moya.funch.usecase

import com.moya.funch.entity.Mbti
import com.moya.funch.repository.MbtiCollectionRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LoadMbtiCollectionUseCaseImpl @Inject constructor(
    private val mbtiCollectionRepository: MbtiCollectionRepository
) : LoadMbtiCollectionUseCase {

    override suspend operator fun invoke(): Flow<List<Mbti>> {
        return mbtiCollectionRepository.loadMbtiCollection()
    }
}

fun interface LoadMbtiCollectionUseCase {
    suspend operator fun invoke(): Flow<List<Mbti>>
}
