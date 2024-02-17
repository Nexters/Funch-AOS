package com.moya.funch.network.service

import com.google.common.truth.Truth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.moya.funch.network.dto.response.BaseResponse
import com.moya.funch.network.dto.response.subway_station.LocationResponse
import com.moya.funch.network.dto.response.subway_station.SubwayStationsResponse
import com.moya.funch.rule.CoroutinesTestExtension
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Retrofit
import retrofit2.create
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockKExtension::class)
@ExtendWith(CoroutinesTestExtension::class)
internal class SubwayServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var subwayStationService: SubwayService

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        subwayStationService =
            Retrofit.Builder().addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    coerceInputValues = true
                }.asConverterFactory("application/json".toMediaType())
            ).baseUrl(mockWebServer.url("")).build().create()
    }

    @Test
    fun `subway를 입력하여 Subway Station List와 Line List를 불러온다`() = runTest {
        // given
        val matchingJson = File("src/test/res/search_subway_stations_result.json").readText()
        val fakeResponse = MockResponse().setBody(matchingJson).setResponseCode(200)
        mockWebServer.enqueue(fakeResponse)
        val expected =
            BaseResponse(
                status = 200,
                message = "OK",
                data = listOf(
                    SubwayStationsResponse(
                        id = "65cdf2d7ffc89209ea09ce65",
                        name = "강남",
                        lines = listOf("TWO", "SINBUNDANG"),
                        location = LocationResponse(
                            latitude = "0.0",
                            longitude = "0.0"
                        )
                    ),
                    SubwayStationsResponse(
                        id = "65cdf2d7ffc89209ea09ce66",
                        name = "강남구청",
                        lines = listOf("BUNDANG", "SEVEN"),
                        location = LocationResponse(
                            latitude = "0.0",
                            longitude = "0.0"
                        )
                    )
                )
            )
        // when
        val actualResponse = subwayStationService.findSubwayStations("강")
        // then
        Truth.assertThat(actualResponse).isEqualTo(expected)
    }

}
