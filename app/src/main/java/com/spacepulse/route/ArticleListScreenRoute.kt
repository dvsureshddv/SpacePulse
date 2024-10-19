package com.spacepulse.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.spacepulse.presentation.ui.ArticleListView
import com.spacepulse.presentation.view_models.NewsViewModel

@Composable
fun ArticleListScreenRoute(
    viewModel: NewsViewModel,
    navController: NavHostController,
) {

    // article list screen
    ArticleListView(navController = navController, newsViewModel = viewModel)
}