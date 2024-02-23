package com.moya.funch.usecase

import com.moya.funch.repository.MemberRepository
import javax.inject.Inject

class DeleteUserProfileUseCaseImpl @Inject constructor(
    private val memberRepository: MemberRepository
) : DeleteUserProfileUseCase {
    override suspend operator fun invoke(): Result<String> {
        return memberRepository.deleteUserProfile()
    }
}

fun interface DeleteUserProfileUseCase {
    suspend operator fun invoke(): Result<String>
}
