package com.moya.funch.collection.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moya.funch.collection.CollectionRoute

const val COLLECTION_ROUTE = "collection"

fun NavController.navigateToCollection(navOptions: NavOptions? = null) = navigate(COLLECTION_ROUTE, navOptions)

fun NavGraphBuilder.collectionScreen(onNavigateToHome: () -> Unit) {
    composable(route = COLLECTION_ROUTE) {
        CollectionRoute(
            onNavigateToHome = onNavigateToHome
        )
    }
}
