package com.moya.funch.network.service

import com.moya.funch.network.dto.request.MemberRequest
import com.moya.funch.network.dto.response.BaseResponse
import com.moya.funch.network.dto.response.member.MemberResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MemberService {

    @POST("api/v1/members")
    suspend fun createMember(@Body body: MemberRequest): BaseResponse<MemberResponse>

    @GET("api/v1/members/{id}")
    suspend fun findMemberById(@Path("id") id: String): BaseResponse<MemberResponse>

    @GET("api/v1/members")
    suspend fun findMemberByDeviceNumber(@Query("deviceNumber") deviceNumber: String): BaseResponse<MemberResponse>
}
