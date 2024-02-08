package com.moya.funch.usecase

import com.moya.funch.entity.match.Matching
import com.moya.funch.repository.MatchingRepository
import javax.inject.Inject

class MatchProfileUseCaseImpl @Inject constructor(
    private val matchingRepository: MatchingRepository
) : MatchProfileUseCase {
    override suspend operator fun invoke(targetCode: String): Matching = matchingRepository.matchProfile(targetCode)
}

fun interface MatchProfileUseCase {
    suspend operator fun invoke(targetCode: String): Matching
}
