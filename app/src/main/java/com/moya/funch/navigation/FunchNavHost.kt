package com.moya.funch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.moya.funch.match.navigation.matchingScreen
import com.moya.funch.match.navigation.navigateToMatching

@Composable
fun FunchNavHost(hasProfile: Boolean, navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = determineStartDestination(hasProfile)
    ) {
        with(navController) {
            profileGraph(
                onNavigateToHome = ::navigateToHome,
                onCloseMyProfile = ::closeMyProfile
            )
            homeScreen(
                onNavigateToMatching = ::onNavigateToMatching,
                onNavigateToMyProfile = ::navigateToMyProfile
            )
            matchingScreen(onClose = { popBackStack(HOME_ROUTE, false) })
        }
    }
}

private val singleTopNavOptions = navOptions {
    launchSingleTop = true
    popUpTo(HOME_ROUTE)
}

private fun NavController.onNavigateToMatching(route: String) = navigateToMatching(route, singleTopNavOptions)

private fun determineStartDestination(hasProfile: Boolean): String {
    return if (hasProfile) HOME_ROUTE else PROFILE_GRAPH_ROUTE
}
