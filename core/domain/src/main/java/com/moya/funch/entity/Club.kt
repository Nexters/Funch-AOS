package com.moya.funch.entity

enum class Club(val label: String) {
    NEXTERS("넥스터즈"), SOPT("SOPT"),
    DEPROMEET("Depromeet"), IDLE("");

    companion object {
        fun find(clubName: String): Club {
            return requireNotNull(entries.find { it.label == clubName }) { "Club : $clubName not found" }
        }
    }
}
