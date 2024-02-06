package com.moya.funch.entity

enum class Club(val label: String) {
    NEXTERS("넥스터즈"),
    SOPT("SOPT"),
    DEPROMEET("Depromeet"),
    IDLE("idle"),
    ;

    companion object {
        fun of(clubName: String): Club {
            return requireNotNull(entries.find { it.label == clubName }) { "Club : $clubName not found" }
        }
    }
}
