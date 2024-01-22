package com.moya.funch.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalFunchColors = staticCompositionLocalOf<FunchColorSchema> {
    error("No FunchColors provided")
}
private val LocalFunchTypography = staticCompositionLocalOf<FunchTypography> {
    error("No FunchTypography provided")
}

private val DarkGradientColors =
    GradientColors(top = Gray900, bottom = Gray900)

private val DarkAndroidBackgroundTheme = BackgroundTheme(color = Gray900)

object FunchTheme {
    val colors: FunchColorSchema @Composable get() = LocalFunchColors.current
    val typography: FunchTypography @Composable get() = LocalFunchTypography.current
}

@Composable
fun ProvidePophoryColorAndTypography(
    colors: FunchColorSchema,
    typography: FunchTypography,
    content: @Composable () -> Unit,
) {
    val provideColors = remember { colors.copy() }
    provideColors.update(colors)
    val provideTypography = remember { typography.copy() }
    provideTypography.update(typography)
    CompositionLocalProvider(
        LocalFunchColors provides provideColors, LocalFunchTypography provides provideTypography, content = content
    )
}

@Composable
fun FunchTheme(
    content: @Composable () -> Unit,
) {
    // this version provides only dark theme
    val colors = funchDarkColorSchema()
    val gradientColors = DarkGradientColors
    val typography = funchTypography()
    val backgroundTheme = DarkAndroidBackgroundTheme

    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
    ) {
        ProvidePophoryColorAndTypography(colors, typography) {
            MaterialTheme(content = content)
        }
    }
}

