package com.moya.funch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.moya.funch.match.navigation.matchingScreen
import com.moya.funch.match.navigation.navigateToMatching
import com.moya.funch.onboarding.navigation.ON_BOARDING_ROUTE
import com.moya.funch.onboarding.navigation.onBoardingScreen

@Composable
fun FunchNavHost(hasProfile: Boolean, navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = determineStartDestination(hasProfile)
    ) {
        with(navController) {
            profileGraph(
                onNavigateToHome = ::onNavigateToHome,
                onCloseMyProfile = ::onCloseMyProfile,
                onNavigateCreateProfile = ::onNavigateToCreateProfile
            )
            homeScreen(
                onNavigateToMatching = ::onNavigateToMatching,
                onNavigateToMyProfile = ::onNavigateToMyProfile
            )
            matchingScreen(onClose = { popBackStack(HOME_ROUTE, false) })
            onBoardingScreen(onNavigateToCreateProfile = ::navigateToCreateProfile)
        }
    }
}

private fun NavController.onNavigateToCreateProfile() =
    navigateToCreateProfile(navOptions { popUpTo(graph.id) { inclusive = true } })

private fun NavController.onNavigateToMyProfile() = navigateToMyProfile(singleTopNavOptions)

private fun NavController.onNavigateToMatching(route: String) = navigateToMatching(route, singleTopNavOptions)

private val singleTopNavOptions = navOptions {
    launchSingleTop = true
    popUpTo(HOME_ROUTE)
}

private fun determineStartDestination(hasProfile: Boolean): String {
    return if (hasProfile) HOME_ROUTE else ON_BOARDING_ROUTE
}
