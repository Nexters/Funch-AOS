package com.moya.funch.datasource.remote

import com.moya.funch.datastore.UserDataStore
import com.moya.funch.network.service.MemberService
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
internal class RemoteUserDataSourceImplTest {

    @RelaxedMockK
    lateinit var userDataStore: UserDataStore

    @RelaxedMockK
    lateinit var memberService: MemberService
    private lateinit var remoteUserDataSource: RemoteUserDataSource

    @BeforeEach
    fun setUp() {
        remoteUserDataSource = RemoteUserDataSourceImpl(
            userDataStore,
            memberService
        )
    }

    @Test
    fun `id가 있으면 id로 profile을 불러온다`() = runTest {
        //given
        coEvery { userDataStore.hasUserId() } returns true
        //when
        remoteUserDataSource.fetchUserProfile()
        //then
        assertAll(
            { coVerify(exactly = 1) { memberService.findMemberById(any()) } },
            { coVerify(exactly = 0) { memberService.findMemberByDeviceNumber(any()) } }
        )
    }

    @Test
    fun `id가 없으면 device id로 profile을 불러온다`() = runTest {
        //given
        coEvery { userDataStore.hasUserId() } returns false
        //when
        remoteUserDataSource.fetchUserProfile()
        //then
        assertAll(
            { coVerify(exactly = 0) { memberService.findMemberById(any()) } },
            { coVerify(exactly = 1) { memberService.findMemberByDeviceNumber(any()) } }
        )
    }

    companion object {
        @JvmField
        @RegisterExtension
        val coroutineExtension = CoroutinesTestExtension()
    }
}

