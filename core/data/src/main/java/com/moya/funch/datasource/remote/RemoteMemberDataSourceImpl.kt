package com.moya.funch.datasource.remote

import com.moya.funch.datastore.UserDataStore
import com.moya.funch.model.ProfileModel
import com.moya.funch.model.toModel
import com.moya.funch.network.service.MemberService
import javax.inject.Inject

class RemoteMemberDataSourceImpl @Inject constructor(
    private val memberService: MemberService,
    private val userDataStore: UserDataStore
) : RemoteMemberDataSource {
    override suspend fun fetchMemberProfile(): Result<ProfileModel> {
        return runCatching {
            memberService.findMemberById(userDataStore.userId)
        }.map { it.data.toModel() }
    }
}
