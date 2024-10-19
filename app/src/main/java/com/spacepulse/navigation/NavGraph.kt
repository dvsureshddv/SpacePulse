package com.spacepulse.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.spacepulse.presentation.view_models.NewsViewModel
import com.spacepulse.route.ArticleDetailsScreenRoute
import com.spacepulse.route.ArticleListScreenRoute


//Nav graph builder for various routes
fun NavGraphBuilder.articleListRoute(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
) {
    //article listing
    composable(route = Routes.ARTICLE_LIST_SCREEN) {
        ArticleListScreenRoute(
            viewModel = newsViewModel,
            navController = navController
        )
    }
}


fun NavGraphBuilder.articleDetailsRoute(
    newsViewModel: NewsViewModel,
    navController: NavHostController
) {
    //article details
    composable(route = Routes.ARTICLE_DETAILS_SCREEN) {
        ArticleDetailsScreenRoute(
            newsViewModel = newsViewModel,
            navController = navController
        )
    }
}

