package com.moya.funch.entity.match

import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.profile.Profile

data class Matching(
    val profile: Profile = Profile(),
    val similarity: Int = 0,
    val chemistrys: List<Chemistry> = emptyList(),
    val recommends: List<Recommend> = emptyList(),
) {
    init {
        require(similarity in 0..100) {
            "similarity must be in 0..100"
        }
    }


    fun matches(job: Job, recommends: List<Recommend>): Boolean {
        return recommends.any { recommend ->
            (job.name == recommend.title) || (job.krName == recommend.title)
        }
    }

    fun matches(club: Club, recommends: List<Recommend>): Boolean {
        return recommends.any { recommend ->
            (club.name == recommend.title) || (club.label == recommend.title)
        }
    }

    fun matches(mbti: Mbti, recommends: List<Recommend>): Boolean {
        return recommends.any { recommend ->
            mbti.name == recommend.title
        }
    }

    fun matches(blood: Blood, recommends: List<Recommend>): Boolean {
        return recommends.any { recommend ->
            (blood.name == recommend.title) || (blood.type == recommend.title)
        }
    }

    fun matches(subway: SubwayStation, recommends: List<Recommend>): Boolean {
        return recommends.any { recommend ->
            subway.name == recommend.title
        }
    }

}
