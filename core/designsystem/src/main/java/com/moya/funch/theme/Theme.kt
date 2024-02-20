package com.moya.funch.theme

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview

private val LocalFunchColors =
    staticCompositionLocalOf<FunchColorSchema> {
        error("No FunchColors provided")
    }
private val LocalFunchTypography =
    staticCompositionLocalOf<FunchTypography> {
        error("No FunchTypography provided")
    }

private val DarkGradientColors = GradientColors(top = Gray900, bottom = Gray900)

private val DarkAndroidBackgroundTheme = BackgroundTheme(color = Gray900)

@Composable
private fun statusBar() {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Gray900.toArgb()
            window.navigationBarColor = Gray900.toArgb()
        }
    }
}

object FunchTheme {
    val colors: FunchColorSchema @Composable get() = LocalFunchColors.current
    val typography: FunchTypography @Composable get() = LocalFunchTypography.current
    val shapes: FunchShapes @Composable get() = LocalFunchShapes.current
}

@Composable
fun ProvideFunchProperty(colors: FunchColorSchema, typography: FunchTypography, content: @Composable () -> Unit) {
    val provideColors = remember { colors.copy() }
    provideColors.update(colors)
    val provideTypography = remember { typography.copy() }
    provideTypography.update(typography)

    statusBar()

    val provideShape = remember { FunchShapes() }
    CompositionLocalProvider(
        LocalFunchColors provides provideColors,
        LocalFunchTypography provides provideTypography,
        LocalFunchShapes provides provideShape,
        content = content
    )
}

@Composable
fun FunchTheme(content: @Composable () -> Unit) {
    // this version provides only dark theme
    val colors = funchDarkColorSchema()
    val gradientColors = DarkGradientColors
    val typography = funchTypography
    val backgroundTheme = DarkAndroidBackgroundTheme

    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme
    ) {
        ProvideFunchProperty(colors, typography) {
            MaterialTheme(content = content)
        }
    }
}

// TODO : 추후 삭제
@Preview("FunchTheme 예시")
@Composable
private fun NiaThemePreview() {
    FunchTheme {
        val color = LocalBackgroundTheme.current.color
        Surface(
            color = color
        ) {
            Column {
                Text(
                    text = "Hello, Funch!",
                    color = FunchTheme.colors.white,
                    style = FunchTheme.typography.t1
                )
                Text(
                    text = "Hello, Funch!",
                    color = FunchTheme.colors.white,
                    style = FunchTheme.typography.sbt1
                )
                Button(
                    onClick = { /*TODO*/ },
                    shape = FunchTheme.shapes.small
                ) {
                    Text(text = "Button")
                }
            }
        }
    }
}
