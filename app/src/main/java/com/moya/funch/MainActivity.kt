package com.moya.funch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.moya.funch.datastore.UserDataStore
import com.moya.funch.splash.LoadingScreen
import com.moya.funch.theme.FunchTheme
import com.moya.funch.ui.FunchApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: UserDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            FunchTheme {
                var showLoading by rememberSaveable { mutableStateOf(true) }

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
