package com.spacepulse.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spacepulse.network_module.domain.models.Article

@Composable
fun ArticleList(
    articles: List<Article>,
    modifier: Modifier,
    onArticleClick: (Article) -> Unit
) {

    //list of articles
    LazyColumn(modifier = modifier) {
        items(articles) { article ->
            ArticleCard(article = article) {
                onArticleClick(article)
            }
        }
    }
}