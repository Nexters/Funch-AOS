package com.moya.funch.datasource.remote

import com.moya.funch.datastore.UserDataStore
import com.moya.funch.model.ProfileModel
import com.moya.funch.model.toModel
import com.moya.funch.network.service.MemberService
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userDataStore: UserDataStore,
    private val memberService: MemberService
) : RemoteUserDataSource {
    override suspend fun fetchUserProfile(): Result<ProfileModel> {
        if (userDataStore.hasUserId()) {
            return fetchUserProfileById()
        }
        return fetchUserProfileByDeviceNumber()
    }

    private suspend fun fetchUserProfileById(): Result<ProfileModel> {
        return runCatching { memberService.findMemberById(userDataStore.userId).data }
            .map { it.toModel() }
    }

    private suspend fun fetchUserProfileByDeviceNumber(): Result<ProfileModel> {
        return runCatching { memberService.findMemberByDeviceNumber(userDataStore.deviceId).data }
            .map { it.toModel() }
    }

    override suspend fun createUserProfile(userModel: ProfileModel): Result<ProfileModel> {
        return runCatching {
            val request = userModel.toRequest(userDataStore.deviceId)
            memberService.createMember(request).data
        }.map { it.toModel() }
    }
}
