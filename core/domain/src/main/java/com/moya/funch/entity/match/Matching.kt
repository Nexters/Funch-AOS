package com.moya.funch.entity.match

import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.profile.Profile

data class Matching(
    val profile: Profile,
    val similarity: Int = 0,
    val chemistrys: List<Chemistry> = emptyList(),
    val recommends: List<Recommend> = emptyList(),
    val subways: List<SubwayStation> = emptyList(),
) {
    init {
        require(similarity in 0..100) {
            "similarity must be in 0..100"
        }
    }
}
