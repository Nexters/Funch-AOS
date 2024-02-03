package com.moya.funch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun FunchNavHost(
    hasProfile: Boolean,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = determineStartDestination(hasProfile),
    ) {
        profileGraph(
            onNavigateToHome = navController::navigateToHome,
            onCloseMyProfile = navController::closeMyProfile,
        )
        homeScreen(
            onNavigateToMatching = { /* @Gun Hyung TODO : 매칭 라우터 연결 */ },
            onNavigateToMyProfile = navController::navigateToMyProfile,
        )
    }
}

private fun determineStartDestination(hasProfile: Boolean): String {
    return if (hasProfile) HOME_ROUTE else PROFILE_GRAPH_ROUTE
}
