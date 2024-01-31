package com.moya.funch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.ui.FunchApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunchTheme {
                val backgroundColor = LocalBackgroundTheme.current.color
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor,
                ) {
                    FunchApp(
                        matchingCode = "",
                        onMatchingCodeChange = {},
                        onSearchClick = {},
                        myCode = "",
                        onMyProfileClick = {},
                        viewCount = 12,
                    )
                }
            }
        }
    }
}
