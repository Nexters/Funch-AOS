package com.moya.funch.network.service

import com.moya.funch.network.dto.response.BaseResponse
import com.moya.funch.network.dto.response.subwaystation.SubwayStationsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SubwayService {

    @GET("api/v1/subway-stations/search")
    suspend fun findSubwayStations(@Query("query") subwayStation: String): BaseResponse<List<SubwayStationsResponse>>
}
