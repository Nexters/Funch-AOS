package com.moya.funch.entity.profile

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation

data class Profile(
    val id: String = "",
    val code: String = "",
    val name: String = "",
    val job: Job = Job.IDLE,
    val clubs: List<Club> = emptyList(),
    val mbti: Mbti = Mbti.IDLE,
    val blood: Blood = Blood.IDLE,
    val subways: List<SubwayStation> = emptyList()
) {
    companion object {
        fun default(): Profile {
            return Profile(
                id = "QW2E213EEADF",
                code = "U23C",
                name = "김민수",
                job = Job.DEVELOPER,
                clubs = listOf(Club.NEXTERS, Club.SOPT, Club.DEPROMEET),
                mbti = Mbti.ENFP,
                blood = Blood.A,
                subways = listOf(
                    SubwayStation(
                        "동대문역사문화공원", listOf(
                            SubwayLine.ONE,
                            SubwayLine.FOUR
                        )
                    ),
                    SubwayStation(
                        "초지역", listOf(
                            SubwayLine.TWO,
                            SubwayLine.THREE
                        )
                    )
                )
            )
        }
    }
}
