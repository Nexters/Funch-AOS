package com.moya.funch.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ON_BOARDING_ROUTE = "home"

fun NavGraphBuilder.onBoardingScreen(onNavigateToCreateProfile: () -> Unit) {
    composable(route = ON_BOARDING_ROUTE) {

    }
}
