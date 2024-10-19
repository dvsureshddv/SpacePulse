package com.spacepulse.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.spacepulse.presentation.ui.ArticleDetailsView
import com.spacepulse.presentation.view_models.NewsViewModel

//article details routing
@Composable
fun ArticleDetailsScreenRoute(
    newsViewModel: NewsViewModel,
    navController: NavHostController
) {
    // article detail screen
    ArticleDetailsView(newsViewModel = newsViewModel, navController = navController)
}