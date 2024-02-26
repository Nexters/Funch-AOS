package com.moya.funch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.moya.funch.CreateProfileRoute
import com.moya.funch.MyProfileRoute

const val PROFILE_GRAPH_ROUTE = "profile_graph"

fun NavController.navigateToMyProfile(navOptions: NavOptions? = null) =
    navigate(ProfileScreens.MyProfile.route, navOptions)

fun NavController.navigateToCreateProfile(navOptions: NavOptions? = null) =
    navigate(ProfileScreens.Create.route, navOptions)

fun NavController.onCloseMyProfile() = popBackStack()

fun NavGraphBuilder.profileGraph(
    onNavigateToHome: () -> Unit,
    onCloseMyProfile: () -> Unit,
    onNavigateCreateProfile: () -> Unit
) {
    navigation(
        route = PROFILE_GRAPH_ROUTE,
        startDestination = ProfileScreens.Create.route
    ) {
        composable(route = ProfileScreens.Create.route) {
            CreateProfileRoute(
                onNavigateToHome = onNavigateToHome
            )
        }
        composable(route = ProfileScreens.MyProfile.route) {
            MyProfileRoute(
                onCloseMyProfile = onCloseMyProfile,
                onNavigateCreateProfile = onNavigateCreateProfile
            )
        }
    }
}

internal sealed class ProfileScreens(val route: String) {
    data object MyProfile : ProfileScreens("my_profile")

    data object Create : ProfileScreens("create_profile")
}
