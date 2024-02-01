package com.moya.funch.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moya.funch.navigation.FunchNavHost
import com.moya.funch.theme.LocalBackgroundTheme

@Composable
fun FunchApp() {
    val backgroundColor = LocalBackgroundTheme.current.color

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor,
    ) {
        FunchNavHost(
            hasProfile = false,
        )
    }
}
