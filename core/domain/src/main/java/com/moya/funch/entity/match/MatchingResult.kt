package com.moya.funch.entity.match

import com.moya.funch.entity.profile.Profile
import com.moya.funch.entity.SubwayStation

data class MatchingResult(
    val profile: Profile = Profile(),
    val similarity: Int = 0,
    val chemistrys: List<Chemistry> = emptyList(),
    val recommends: List<Recommend> = emptyList(),
    val subways: List<SubwayStation> = emptyList(),
){
    init {
        require(similarity in 0 .. 100){
            "similarity must be in 0..100"
        }
    }
}
