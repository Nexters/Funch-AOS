package com.moya.funch.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.moya.funch.designsystem.R

private val spoqaHanSansNeo = FontFamily(
    Font(
        R.font.spoqa_han_sans_neo_bold,
        FontWeight.Bold,
        FontStyle.Normal
    ),
    Font(
        R.font.spoqa_han_sans_neo_medium,
        FontWeight.Medium,
        FontStyle.Normal
    ),
    Font(
        R.font.spoqa_han_sans_neo_regular,
        FontWeight.Normal,
        FontStyle.Normal
    ),
)

@Stable
class FunchTypography internal constructor(
    t1: TextStyle,
    t2: TextStyle,
    sbt1: TextStyle,
    sbt2: TextStyle,
    b: TextStyle,
    caption: TextStyle,
) {
    var t1: TextStyle by mutableStateOf(t1)
        private set
    var t2: TextStyle by mutableStateOf(t2)
        private set
    var sbt1: TextStyle by mutableStateOf(sbt1)
        private set
    var sbt2: TextStyle by mutableStateOf(sbt2)
        private set
    var b: TextStyle by mutableStateOf(b)
        private set
    var caption: TextStyle by mutableStateOf(caption)
        private set

    fun copy(
        t1: TextStyle = this.t1,
        t2: TextStyle = this.t2,
        sbt1: TextStyle = this.sbt1,
        sbt2: TextStyle = this.sbt2,
        b: TextStyle = this.b,
        caption: TextStyle = this.caption,
    ): FunchTypography = FunchTypography(
        t1 = t1,
        t2 = t2,
        sbt1 = sbt1,
        sbt2 = sbt2,
        b = b,
        caption = caption,
    )

    fun update(other: FunchTypography) {
        t1 = other.t1
        t2 = other.t2
        sbt1 = other.sbt1
        sbt2 = other.sbt2
        b = other.b
        caption = other.caption
    }
}

internal fun funchTypography(): FunchTypography {
    return FunchTypography(
        t1 = TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            letterSpacing = (-0.02).em,
            lineHeight = 28.6.sp
        ),
        t2 = TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = (-0.02).em,
            lineHeight = 26.sp
        ),
        sbt1 = TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            letterSpacing = (-0.02).em,
            lineHeight = 23.4.sp
        ),
        sbt2 = TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = (-0.02).sp,
            lineHeight = 20.8.sp
        ),
        b = TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = (-0.03).em,
            lineHeight = 21.sp
        ),
        caption = TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = (-0.03).em,
            lineHeight = 18.sp
        )
    )
}

internal val funchTypography = funchTypography()
