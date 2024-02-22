package com.moya.funch.datasource.remote

import com.moya.funch.model.ProfileModel
import com.moya.funch.model.toModel
import com.moya.funch.network.service.MemberService
import javax.inject.Inject

class RemoteMemberDataSourceImpl @Inject constructor(
    private val memberService: MemberService
) : RemoteMemberDataSource {
    override suspend fun fetchMemberProfile(id: String): Result<ProfileModel> {
        return runCatching {
            memberService.findMemberById(id)
        }.mapCatching { it.data.toModel() }
    }
}
