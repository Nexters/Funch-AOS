package com.moya.funch.repository

import com.moya.funch.entity.profile.Profile

interface MemberRepository {
    suspend fun fetchUserProfile(): Result<Profile>

    suspend fun createUserProfile(profile: Profile): Result<Profile>

    suspend fun fetchUserViewCount(): Result<Int>
}
