package com.moya.funch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.moya.funch.datastore.UserDataStore
import com.moya.funch.splash.LoadingScreen
import com.moya.funch.theme.FunchTheme
import com.moya.funch.ui.FunchApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: UserDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            FunchTheme {
                var showLoading by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(1500)
                    showLoading = false
                }

                if (showLoading) {
                    LoadingScreen()
                } else {
                    FunchApp(dataStore = dataStore)
                }
            }
        }
    }
}
