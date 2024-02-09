package com.moya.funch.repository

import com.google.common.truth.Truth.assertThat
import com.moya.funch.datasource.local.LocalUserDataSource
import com.moya.funch.datasource.remote.RemoteMemberDataSource
import com.moya.funch.datasource.remote.RemoteUserDataSource
import com.moya.funch.entity.profile.Profile
import com.moya.funch.model.ProfileModel
import com.moya.funch.rule.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockKExtension::class)
internal class MemberRepositoryImplTest {
    @RelaxedMockK
    private lateinit var remoteUserDataSource: RemoteUserDataSource

    @RelaxedMockK
    private lateinit var localUserDataSource: LocalUserDataSource

    @RelaxedMockK
    private lateinit var remoteMemberDataSource: RemoteMemberDataSource
    private lateinit var memberRepository: MemberRepository

    @BeforeEach
    fun setUp() {
        memberRepository = MemberRepositoryImpl(
            remoteUserDataSource,
            localUserDataSource,
            remoteMemberDataSource
        )
    }

    @Test
    fun `local에 User Profile이 있으면 불러온다`() = runTest {
        //given
        coEvery { localUserDataSource.fetchUserProfile() } returns Result.success(
            ProfileModel(
                userCode = "NONE",
                userId = "userId",
                name = "userName",
                jobGroup = "개발자",
                bloodType = "O",
                clubs = setOf("SOPT"),
                subwayName = "subwayName",
                subwayLines = emptySet(),
                mbti = "INTJ"
            )
        )
        //when
        val result = memberRepository.fetchUserProfile()
        //then
        assertAll(
            { coVerify(exactly = 1) { localUserDataSource.fetchUserProfile() } },
            { coVerify(exactly = 0) { remoteUserDataSource.fetchUserProfile() } },
            { coVerify(exactly = 0) { localUserDataSource.saveUserProfile(any()) } },
            { assertThat(result.isSuccess).isTrue() }
        )
    }

    @Test
    fun `local에 User Profile이 없으면 remote에서 불러온다`() = runTest {
        //given
        coEvery { localUserDataSource.fetchUserProfile() } returns Result.failure(
            Exception("User Profile not found")
        )
        coEvery { remoteUserDataSource.fetchUserProfile() } returns Result.success(
            ProfileModel(
                userCode = "NONE",
                userId = "userId",
                name = "userName",
                jobGroup = "개발자",
                bloodType = "O",
                clubs = setOf("SOPT"),
                subwayName = "subwayName",
                subwayLines = emptySet(),
                mbti = "INTJ"
            )
        )
        coEvery { localUserDataSource.saveUserProfile(any()) } returns Result.success(
            Unit
        )
        //when
        val result = memberRepository.fetchUserProfile()
        //then
        assertAll(
            { coVerify(exactly = 1) { localUserDataSource.fetchUserProfile() } },
            { coVerify(exactly = 1) { remoteUserDataSource.fetchUserProfile() } },
            { coVerify(exactly = 1) { localUserDataSource.saveUserProfile(any()) } },
            { assertThat(result.isSuccess).isTrue() }
        )
    }

    @Test
    fun `User Profile을 만들고 local에 저장한다`() = runTest {
        //given
        val profile = Profile()
        coEvery { remoteUserDataSource.createUserProfile(any()) } returns Result.success(
            ProfileModel(
                userCode = "NONE",
                userId = "userId",
                name = "userName",
                jobGroup = "개발자",
                bloodType = "O",
                clubs = setOf("SOPT"),
                subwayName = "subwayName",
                subwayLines = emptySet(),
                mbti = "INTJ"
            )
        )
        //when
        val profileResult = memberRepository.createUserProfile(profile)
        //then
        assertAll(
            { coVerify(exactly = 1) { remoteUserDataSource.createUserProfile(any()) } },
            { coVerify(exactly = 1) { localUserDataSource.saveUserProfile(any()) } },
            { assertThat(profileResult.isSuccess).isTrue() }
        )
    }

    companion object {
        @JvmField
        @RegisterExtension
        val coroutineExtension = CoroutinesTestExtension()
    }
}
