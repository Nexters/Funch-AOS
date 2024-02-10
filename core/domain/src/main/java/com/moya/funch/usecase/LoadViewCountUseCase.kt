package com.moya.funch.usecase

import com.moya.funch.repository.MemberRepository
import javax.inject.Inject

class LoadViewCountUseCaseImpl @Inject constructor(
    private val memberRepository: MemberRepository
) : LoadViewCountUseCase {
    override suspend fun invoke(): Result<Int> {
        return memberRepository.fetchUserViewCount()
    }
}

fun interface LoadViewCountUseCase {
    suspend operator fun invoke(): Result<Int>
}
