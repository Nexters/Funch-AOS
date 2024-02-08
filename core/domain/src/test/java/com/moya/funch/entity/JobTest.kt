package com.moya.funch.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class JobTest {
    @Test
    fun `직군에 해당하지 않는 한글 이름으로 찾을 수 없다`() {
        assertThrows<IllegalArgumentException>("Job : 디발자 not found") {
            Job.of("디발자")
        }
    }
}
