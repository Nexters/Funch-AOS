package com.moya.funch.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

internal class ClubTest {
    @Test
    fun `Club의 label혹은 name에 해당하는 이름으로 찾을 수 있다 `() {
        assertAll(
            { Club.of("넥스터즈") },
            { Club.of("NEXTERS") },
            { Club.of("SOPT") },
            { Club.of("Depromeet") },
            { Club.of("DEPROMEET") },
            {
                assertThrows<IllegalArgumentException>("Club : 닭아리 not found") {
                    Club.of("닭아리")
                }
            }

        )
    }
}
