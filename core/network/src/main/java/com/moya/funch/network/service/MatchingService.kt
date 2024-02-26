package com.moya.funch.network.service

import com.moya.funch.network.dto.request.MatchingRequest
import com.moya.funch.network.dto.response.BaseResponse
import com.moya.funch.network.dto.response.match.CanMatchResponse
import com.moya.funch.network.dto.response.match.MatchingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MatchingService {
    @POST("api/v1/matching")
    suspend fun matchProfile(@Body body: MatchingRequest): BaseResponse<MatchingResponse>

    @GET("api/v1/matching/{targetMemberCode}")
    suspend fun canMatchProfile(@Path("targetMemberCode") code: String): CanMatchResponse
}
