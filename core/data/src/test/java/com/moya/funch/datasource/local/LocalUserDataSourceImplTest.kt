package com.moya.funch.datasource.local

import com.google.common.truth.Truth.assertThat
import com.moya.funch.datastore.UserDataStore
import com.moya.funch.model.ProfileModel
import com.moya.funch.rule.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockKExtension::class)
internal class LocalUserDataSourceImplTest {

    @RelaxedMockK
    lateinit var userDataStore: UserDataStore
    private lateinit var localUserDataSource: LocalUserDataSource

    @BeforeEach
    fun setUp() {
        localUserDataSource = LocalUserDataSourceImpl(
            userDataStore
        )
    }

    @Test
    fun `Id가 있으면 내부 저장소에서 Profile을 가져온다`() = runTest {
        // given
        val expectedProfile = ProfileModel(
            userCode = "userCode",
            userId = "userId",
            name = "userName",
            jobGroup = "jobGroup",
            bloodType = "bloodType",
            clubs = setOf("clubs"),
            subwayName = "subwayName",
            subwayLines = setOf("subwayLines"),
            mbti = "mbti"
        )

        coEvery { userDataStore.hasUserId() } returns true
        coEvery { userDataStore.userCode } returns "userCode"
        coEvery { userDataStore.userId } returns "userId"
        coEvery { userDataStore.userName } returns "userName"
        coEvery { userDataStore.jobGroup } returns "jobGroup"
        coEvery { userDataStore.bloodType } returns "bloodType"
        coEvery { userDataStore.clubs } returns setOf("clubs")
        coEvery { userDataStore.subwayName } returns "subwayName"
        coEvery { userDataStore.subwayLines } returns setOf("subwayLines")
        coEvery { userDataStore.mbti } returns "mbti"
        // when
        val actualResult: Result<ProfileModel> = localUserDataSource.fetchUserProfile()
        // then
        assertAll(
            { coVerify(exactly = 1) { userDataStore.hasUserId() } },
            { coVerify(exactly = 1) { userDataStore.userCode } },
            { coVerify(exactly = 1) { userDataStore.mbti } },
            { assertThat(actualResult.isSuccess).isTrue() },
            { assertThat(actualResult.getOrNull()).isEqualTo(expectedProfile) }
        )
    }

    @Test
    fun `Id가 없으면 Profile 정보를 가져올 수 없다`() = runTest {
        // given
        coEvery { userDataStore.hasUserId() } returns false
        // when
        val actualResult: Result<ProfileModel> = localUserDataSource.fetchUserProfile()
        // then
        assertAll(
            { coVerify(exactly = 1) { userDataStore.hasUserId() } },
            { assertThat(actualResult.isFailure).isTrue() }
        )
    }

    @Test
    fun `Profile을 내부 저장소에 저장한다`() = runTest {
        // given
        val profile = ProfileModel(
            userCode = "userCode",
            userId = "userId",
            name = "userName",
            jobGroup = "jobGroup",
            bloodType = "bloodType",
            clubs = setOf("clubs"),
            subwayName = "subwayName",
            subwayLines = setOf("subwayLines"),
            mbti = "mbti"
        )
        // when
        val actualResult: Result<Unit> = localUserDataSource.saveUserProfile(profile)
        // then
        assertAll(
            { coVerify(exactly = 1) { userDataStore.clear() } },
            { coVerify(exactly = 1) { userDataStore.userCode = profile.userCode } },
            { coVerify(exactly = 1) { userDataStore.mbti = profile.mbti } },
            { assertThat(actualResult.isSuccess).isTrue() }
        )
    }

    @Test
    fun `MbtiCollection이 비어있으면 emptyList를 가져온다`() = runTest {
        // given
        coEvery { userDataStore.mbtiCollection.isEmpty() } returns false
        // when
        val actualResult = localUserDataSource.fetchUserMbtiCollection().first()
        // then
        assertAll(
            { coVerify(exactly = 1) { userDataStore.mbtiCollection } },
            { assertThat(actualResult).isEmpty() }
        )
    }

    companion object {
        @JvmField
        @RegisterExtension
        val coroutineExtension = CoroutinesTestExtension()
    }
}
