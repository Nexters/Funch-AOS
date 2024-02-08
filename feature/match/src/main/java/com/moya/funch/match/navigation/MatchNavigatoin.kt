package com.moya.funch.match.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moya.funch.match.MatchRoute

private const val MATCH_ROUTE = "match/{code}"
private const val MEMBER_CODE_KEY = "code"
private const val NO_MEMBER_CODE = "no member code"

fun NavController.navigateToMatching(memberCode: String, navOptions: NavOptions? = null) =
    navigate(createMatchRoute(memberCode), navOptions)

fun NavGraphBuilder.matchingScreen(onClose: () -> Unit) {
    composable(
        route = MATCH_ROUTE,
        arguments = listOf(
            navArgument(MEMBER_CODE_KEY) {
                type = NavType.StringType
                defaultValue = NO_MEMBER_CODE
            }
        )
    ) { backStackEntry ->
        val code = backStackEntry.arguments?.getString(MEMBER_CODE_KEY) ?: NO_MEMBER_CODE
        MatchRoute(code = code, onClose = onClose)
    }
}

private fun createMatchRoute(memberCode: String) = MATCH_ROUTE.replace("{$MEMBER_CODE_KEY}", memberCode)
