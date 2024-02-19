package com.moya.funch.network.service

import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.moya.funch.network.dto.request.MatchingRequest
import com.moya.funch.network.dto.response.BaseResponse
import com.moya.funch.network.dto.response.match.ChemistryResponse
import com.moya.funch.network.dto.response.match.MatchingResponse
import com.moya.funch.network.dto.response.match.ProfileResponse
import com.moya.funch.network.dto.response.match.RecommendResponse
import com.moya.funch.network.dto.response.match.SubwayResponse
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
import org.junit.jupiter.api.extension.RegisterExtension
import retrofit2.Retrofit
import retrofit2.create
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockKExtension::class)
@ExtendWith(CoroutinesTestExtension::class)
internal class MatchingServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var matchingService: MatchingService

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        matchingService =
            Retrofit.Builder().addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    coerceInputValues = true
                }.asConverterFactory("application/json".toMediaType())
            ).baseUrl(mockWebServer.url("")).build().create()
    }

    @Test
    fun `내 id와 상대방의 code로 Matching 결과를 불러올 수 있다`() = runTest {
        // given
        val matchingJson = File("src/test/res/matching_result.json").readText()
        val fakeResponse = MockResponse().setBody(matchingJson).setResponseCode(200)
        mockWebServer.enqueue(fakeResponse)
        val expected =
            BaseResponse(
                status = 200,
                message = "OK",
                data =
                MatchingResponse(
                    profile =
                    ProfileResponse(
                        name = "aaa",
                        jobGroup = "개발자",
                        clubs = listOf("DEPROMEET"),
                        mbti = "ENFJ",
                        bloodType = "AB",
                        subways = listOf(
                            SubwayResponse(
                                lines = listOf("ONE", "FOUR"),
                                name = "서울역"
                            )
                        )
                    ),
                    similarity = 40,
                    chemistryInfos =
                    listOf(
                        ChemistryResponse(
                            "기막힌 타이밍에 등장한 너!",
                            "미정"
                        ),
                        ChemistryResponse(
                            "서로 비슷한 똑! 닮은 꼴",
                            "미정"
                        )
                    ),
                    recommends =
                    listOf(
                        RecommendResponse("ENFJ"),
                        RecommendResponse("전갈자리")
                    ),
                    subwayChemistry = ChemistryResponse(
                        "ONE",
                        "1호선"
                    )
                )
            )
        // when
        val actualResponse =
            matchingService.matchProfile(
                MatchingRequest(
                    userId = "65b6c543ebe5db753688b9dd",
                    targetCode = "7O2K"
                )
            )
        // then
        assertThat(actualResponse).isEqualTo(expected)
    }

    companion object {
        @JvmField
        @RegisterExtension
        val coroutineExtension = CoroutinesTestExtension()
    }
}
