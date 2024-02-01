package com.moya.funch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.moya.funch.HomeRoute

const val HOME_GRAPH_ROUTE = "home_graph"

fun NavController.navigateToHome() =
    navigate(Home.FunchHome.route) {
        popUpTo(graph.id)
    }

fun NavGraphBuilder.homeGraph(
    onNavigateToMyProfile: () -> Unit,
    onNavigateToMatching: () -> Unit,
) {
    navigation(
        route = HOME_GRAPH_ROUTE,
        startDestination = Home.FunchHome.route,
    ) {
        composable(route = Home.FunchHome.route) {
            HomeRoute(
                onNavigateToMatching = onNavigateToMatching,
                onNavigateToMyProfile = onNavigateToMyProfile,
            )
        }
    }
}

internal sealed class Home(val route: String) {
    data object FunchHome : Home("home")
}
