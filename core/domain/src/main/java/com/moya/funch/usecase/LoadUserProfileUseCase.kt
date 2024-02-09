package com.moya.funch.usecase

import com.moya.funch.entity.profile.Profile
import com.moya.funch.repository.MemberRepository
import javax.inject.Inject

class LoadUserProfileUseCaseImpl @Inject constructor(
    private val memberRepository: MemberRepository
) : LoadUserProfileUseCase {
    override suspend operator fun invoke(): Result<Profile> {
        return memberRepository.fetchUserProfile()
    }
}

fun interface LoadUserProfileUseCase {

    suspend operator fun invoke(): Result<Profile>
}
