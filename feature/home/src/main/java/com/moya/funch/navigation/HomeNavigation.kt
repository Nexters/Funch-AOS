package com.moya.funch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moya.funch.HomeRoute

const val HOME_ROUTE = "home"

fun NavController.onNavigateToHome() = navigate(HOME_ROUTE) {
    popUpTo(graph.id)
}

fun NavGraphBuilder.homeScreen(
    onNavigateToMyProfile: () -> Unit,
    onNavigateToMatching: (String) -> Unit,
    onNavigateToCollection: () -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            onNavigateToMatching = onNavigateToMatching,
            onNavigateToMyProfile = onNavigateToMyProfile,
            onNavigateToCollection = onNavigateToCollection
        )
    }
}
