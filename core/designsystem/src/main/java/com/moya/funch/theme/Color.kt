package com.moya.funch.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

internal val Coral500 = Color(0xFFF86E6F)
internal val Lemon500 = Color(0xFFFFE83B)
internal val Lemon600 = Color(0xFFE1CA13)
internal val Lemon900 = Color(0xFF90720A)
internal val Yellow500 = Color(0xFFFFD240)
internal val Yellow600 = Color(0xFFE1B012)
internal val White = Color(0xFFFFFFFF)
internal val Gray900 = Color(0xFF151515)
internal val Gray800 = Color(0xFF242627)
internal val Gray700 = Color(0xFF2C2C2C)
internal val Gray600 = Color(0xFF363636)
internal val Gray500 = Color(0xFF404040)
internal val Gray400 = Color(0xFF6D6D6D)
internal val Gray300 = Color(0xFF9B9B9B)

@Stable
class FunchColorSchema(
    background: Color,
    error: Color,
    white: Color,
) {
    var background by mutableStateOf(background)
        private set
    var error by mutableStateOf(error)
        private set
    var white by mutableStateOf(white)
        private set

    fun copy(): FunchColorSchema =
        FunchColorSchema(
            background = background,
            error = error,
            white = white,
        )

    fun update(other: FunchColorSchema) {
        background = other.background
        error = other.error
        white = other.white
    }
}

fun funchDarkColorSchema(
    background: Color = Gray900,
    white: Color = White,
    error: Color = Coral500,
): FunchColorSchema {
    return FunchColorSchema(
        white = white,
        background = background,
        error = error,
    )
}
