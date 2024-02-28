package com.moya.funch.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.moya.funch.onboarding.OnBoardingScreen

const val ON_BOARDING_ROUTE = "on_boarding"

fun NavController.navigateToOnBoarding(navOptions: NavOptions? = null) = navigate(ON_BOARDING_ROUTE, navOptions)

fun NavController.navigateToOnBoarding(navAction: NavOptionsBuilder.() -> Unit) = navigate(
    ON_BOARDING_ROUTE,
    navOptions(navAction)
)

fun NavGraphBuilder.onBoardingScreen(onNavigateToCreateProfile: () -> Unit) {
    composable(route = ON_BOARDING_ROUTE) {
        OnBoardingScreen(onNavigateToCreateProfile = onNavigateToCreateProfile)
    }
}
