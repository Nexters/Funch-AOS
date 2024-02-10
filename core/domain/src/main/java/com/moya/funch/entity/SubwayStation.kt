package com.moya.funch.entity

data class SubwayStation(
    val name: String = "",
    val lines: List<SubwayLine> = emptyList()
) {
    init {
        require(lines.distinct().size == lines.size) { "Subway lines must be unique" }
    }
}

enum class SubwayLine {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    AIRPORT,
    EVERLINE,
    GYEONGCHUN,
    GYEONGUI,
    SINBUNDANG,
    SUIN
}
