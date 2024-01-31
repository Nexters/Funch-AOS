package com.moya.funch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.moya.funch.network.dto.request.MatchingRequest
import com.moya.funch.network.service.MatchingService
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.ui.FunchApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var matchingService: MatchingService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // @murjune TODO : 삭제
        lifecycleScope.launch {
            runCatching { // 내꺼 코드 : 7O2K, 상대 코드 : 4V1B
                matchingService.matchProfile(
                    MatchingRequest(
                        userId = "65b6c543ebe5db753688b9dd",
                        targetCode = "7O2K",
                    ),
                )
            }.onSuccess {
                Timber.e("성공 : $it")
            }.onFailure {
                Timber.e(it.stackTraceToString())
            }
        }

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
