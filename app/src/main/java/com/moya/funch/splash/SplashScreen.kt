package com.moya.funch.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.moya.funch.R
import com.moya.funch.theme.FunchTheme

// TODO : 나중에 splash 모듈로 분리

@Composable
fun LoadingScreen() {
    val splashIcon by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.funch_splash_icon_lottie
        )
    )

    val splashBackground by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.funch_splash_background
        )
    )
    Surface(
        color = FunchTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = splashBackground,
                iterations = 1
            )
            LottieAnimation(
                composition = splashIcon,
                iterations = 1
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            val configuration = LocalConfiguration.current
            val screenWidthPx = configuration.screenWidthDp

            val xOffset = with(LocalDensity.current) { -screenWidthPx / 2 }

            val aspectRatio = 115f / 99f
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio)
                    .offset(x = xOffset.dp, y = 0.dp),
                painter = painterResource(id = R.drawable.ic_splash_bg_top),
                contentDescription = "Splash Top icon"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            val configuration = LocalConfiguration.current
            val screenWidthPx = configuration.screenWidthDp
            val xOffset = with(LocalDensity.current) { screenWidthPx / 2 }
            val aspectRatio = 108f / 93f
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio)
                    .offset(x = xOffset.dp, y = 0.dp),
                painter = painterResource(id = R.drawable.ic_splash_bg_bottom),
                contentDescription = "Splash Bottom icon"
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FunchTheme {
        Surface(
            color = FunchTheme.colors.background
        ) {
            LoadingScreen()
        }
    }
}
