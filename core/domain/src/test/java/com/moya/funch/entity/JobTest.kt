package com.moya.funch.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

internal class JobTest {
    @Test
    fun `Job에 해당하는 name 혹은 krName으로 찾을 수 있다`() {
        assertThrows<IllegalArgumentException>("Job : 디발자 not found") {
            Job.of("디발자")
        }
        assertAll(
            { Job.of("DEVELOPER") },
            { Job.of("DESIGNER") },
            { Job.of("개발자") },
            { Job.of("디자이너") },
            {
                assertThrows<IllegalArgumentException>("Job : 디발자 not found") {
                    Job.of("디발자")
                }
            }
        )
    }
}
