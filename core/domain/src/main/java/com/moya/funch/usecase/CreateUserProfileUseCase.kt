package com.moya.funch.usecase

import com.moya.funch.entity.profile.Profile
import com.moya.funch.repository.MemberRepository
import javax.inject.Inject

class CreateUserProfileUseCaseImpl @Inject constructor(
    private val memberRepository: MemberRepository
) : CreateUserProfileUseCase {
    override suspend operator fun invoke(
        profile: Profile
    ): Result<Profile> {
        return memberRepository.createUserProfile(profile)
    }
}

fun interface CreateUserProfileUseCase {
    suspend operator fun invoke(
        profile: Profile
    ): Result<Profile>
}


