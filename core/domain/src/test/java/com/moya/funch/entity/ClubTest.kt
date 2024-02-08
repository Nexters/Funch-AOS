package com.moya.funch.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ClubTest {
    @Test
    fun `동아리에 포함되지 않는 이름으로 찾을 수 없다`() {
        assertThrows<IllegalArgumentException>("Club : 닭아리 not found") {
            Club.of("닭아리")
        }
    }
}
