package com.moya.funch.entity

data class SubwayStation(
    val name: String = "",
    val lines: List<SubwayLine> = emptyList(),
)

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
    BUNDANG,
    EVERLINE,
    GYEONGCHUN,
    GYEONGUI,
    SINBUNDANG,
    SUIN,
}
