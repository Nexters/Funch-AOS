package com.moya.funch.usecase

import com.moya.funch.entity.Mbti
import com.moya.funch.repository.MbtiCollectionRepository
import javax.inject.Inject

class LoadMbtiCollectionUseCaseImpl @Inject constructor(
    private val mbtiCollectionRepository: MbtiCollectionRepository
) : LoadMbtiCollectionUseCase {

    override suspend operator fun invoke(): Result<List<Mbti>> {
        return mbtiCollectionRepository.loadMbtiCollection()
    }
}

fun interface LoadMbtiCollectionUseCase {
    suspend operator fun invoke(): Result<List<Mbti>>
}
