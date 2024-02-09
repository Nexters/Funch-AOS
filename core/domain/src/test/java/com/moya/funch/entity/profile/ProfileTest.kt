package com.moya.funch.entity.profile

import com.moya.funch.entity.Club
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class ProfileTest {

    @Test
    fun `code는 모두 숫자 혹은 대문자 알파벳이다`() {

        assertThrows<IllegalArgumentException>("Code must be all numbers or upper case alphabets") {
            Profile(code = "a123")
        }
    }

    @Test
    fun `code는 4글자다`() {
        assertAll(
            {
                assertThrows<IllegalArgumentException>("Code must not be blank") {
                    Profile(code = "0")
                }
            },
            {
                assertThrows<IllegalArgumentException>("Code must not be blank") {
                    Profile(code = "1")
                }
            },
            {
                assertThrows<IllegalArgumentException>("Code must not be blank") {
                    Profile(code = "12")
                }
            },
            {
                assertThrows<IllegalArgumentException>("Code must not be blank") {
                    Profile(code = "123")
                }
            },
        )
    }

    @Test
    fun `code는 빈칸이면 안된다`() {
        assertThrows<IllegalArgumentException>("Code must not be blank") {
            Profile(code = "")
        }
    }

    @Test
    fun `clubs는 중복되지 않는다`() {
        assertThrows<IllegalArgumentException>("Clubs must be unique") {
            Profile(clubs = listOf(Club.of("SOPT"), Club.of("SOPT")))
        }
    }
}
