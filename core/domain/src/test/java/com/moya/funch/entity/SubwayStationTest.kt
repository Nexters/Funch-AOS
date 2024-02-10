package com.moya.funch.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class SubwayStationTest {
    @Test
    fun `지하철 이름이 중복될 수 없다`() {
        assertThrows<IllegalArgumentException>("Job : 디발자 not found") {
            SubwayStation("목동역", lines = listOf(SubwayLine.FIVE, SubwayLine.FIVE))
        }
    }
}
