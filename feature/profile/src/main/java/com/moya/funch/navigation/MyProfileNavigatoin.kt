package com.moya.funch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.moya.funch.CreateProfileScreen
import com.moya.funch.MyProfileRoute
import com.moya.funch.MyProfileScreen

const val PROFILE_GRAPH_ROUTE = "profile_graph"

fun NavController.navigateToMyProfile() = navigate(Profile.MyProfile.route)

fun NavController.closeMyProfile() = popBackStack() // 텍스트 필드의 값을 남기기 위해 사용

fun NavGraphBuilder.profileGraph(
    onNavigateToHome: () -> Unit,
    onCloseMyProfile: () -> Unit,
) {
    navigation(
        route = PROFILE_GRAPH_ROUTE,
        startDestination = Profile.Create.route,
    ) {
        composable(route = Profile.Create.route) {
            CreateProfileScreen(
                onNavigateToHome = onNavigateToHome,
            )
        }
        composable(route = Profile.MyProfile.route) {
            MyProfileRoute(
                onCloseMyProfile = onCloseMyProfile,
            )
        }
    }
}

internal sealed class Profile(val route: String) {
    data object MyProfile : Profile("my_profile")

    data object Create : Profile("create_profile")
}
