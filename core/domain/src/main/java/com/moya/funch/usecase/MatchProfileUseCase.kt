package com.moya.funch.usecase

import com.moya.funch.entity.match.Matching
import com.moya.funch.repository.MatchingRepository
import javax.inject.Inject

class DefaultMatchProfileUseCase
    @Inject
    constructor(
        private val matchingRepository: MatchingRepository,
    ) : MatchProfileUseCase {
        override suspend operator fun invoke(
            userId: String,
            targetCode: String,
        ): Matching = matchingRepository.matchProfile(userId, targetCode)
    }

fun interface MatchProfileUseCase {
    suspend operator fun invoke(
        userId: String,
        targetCode: String,
    ): Matching
}
