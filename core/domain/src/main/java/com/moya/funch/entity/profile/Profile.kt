package com.moya.funch.entity.profile

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayStation

data class Profile(
    val id: String = "",
    val code: String = "",
    val name: String = "",
    val job: Job = Job.IDLE,
    val clubs: List<Club> = emptyList(),
    val mbti: Mbti = Mbti.IDLE,
    val blood: Blood = Blood.IDLE,
    val subways: List<SubwayStation> = emptyList(),
)
