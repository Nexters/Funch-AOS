package com.moya.funch.usecase

import com.moya.funch.repository.MatchingRepository
import javax.inject.Inject

class CanMatchProfileUseCaseImpl @Inject constructor(
    private val matchingRepository: MatchingRepository
) : CanMatchProfileUseCase {
    override suspend operator fun invoke(targetCode: String): Boolean =
        runCatching { matchingRepository.matchProfile(targetCode) }.isSuccess
}

fun interface CanMatchProfileUseCase {
    suspend operator fun invoke(targetCode: String): Boolean
}
