package com.moya.funch.entity.profile

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayStation

data class Profile(
    val id: String = "",
    val code: String = "NONE",
    val name: String = "",
    val job: Job = Job.IDLE,
    val clubs: List<Club> = emptyList(),
    val mbti: Mbti = Mbti.IDLE,
    val blood: Blood = Blood.IDLE,
    val subways: List<SubwayStation> = emptyList(),
    val viewCount: Int = 0
) {
    init {
        validate(code)
        require(clubs.distinct().size == clubs.size) { "Clubs must be unique" }
    }

    private fun validate(userCode: String) {
        require(userCode.isNotBlank()) { "Code must not be blank" }
        require(userCode.length == 4) { "Code must be 4 letters" }
        require(userCode.all { it in ('A'..'Z') || it in ('0'..'9') }) {
            "Code must be all numbers or upper case alphabets"
        }
    }
}
