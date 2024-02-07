package com.moya.funch.usecase

import com.moya.funch.repository.MatchingRepository
import javax.inject.Inject

class CanMatchProfileUseCaseImpl @Inject constructor(
    private val matchingRepository: MatchingRepository
) : CanMatchProfileUseCase {
    override suspend operator fun invoke(userId: String, targetCode: String): Boolean =
        runCatching { matchingRepository.matchProfile(userId, targetCode) }.isSuccess
}

fun interface CanMatchProfileUseCase {
    suspend operator fun invoke(userId: String, targetCode: String): Boolean
}
