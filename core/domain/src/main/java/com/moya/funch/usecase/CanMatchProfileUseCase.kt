package com.moya.funch.usecase

import com.moya.funch.repository.MatchingRepository
import javax.inject.Inject

class CanMatchProfileUseCaseImpl @Inject constructor(
    private val matchingRepository: MatchingRepository
) : CanMatchProfileUseCase {
    override suspend operator fun invoke(targetCode: String): Boolean =
        runCatching {
            validate(targetCode)
            matchingRepository.matchProfile(targetCode)
        }.isSuccess

    private fun validate(targetCode: String) {
        require(targetCode.isNotBlank())
        require(targetCode.length == 4)
        require(targetCode.all { it in ('A'..'Z') || it in ('0'..'9') })
    }
}

fun interface CanMatchProfileUseCase {
    suspend operator fun invoke(targetCode: String): Boolean
}
