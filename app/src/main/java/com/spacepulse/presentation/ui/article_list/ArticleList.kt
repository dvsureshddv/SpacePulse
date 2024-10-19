package com.spacepulse.presentation.ui.article_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spacepulse.network_module.domain.models.Article
import com.spacepulse.presentation.components.ShimmerEffect

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

//shimmer effect for the rows
@Composable
fun ArticleListShimmer(
    modifier: Modifier,
) {

    //list of articles
    LazyColumn(modifier = modifier) {
        items(10) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    //title view
                    ShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    )

                    //space
                    Spacer(modifier = Modifier.height(8.dp))

                    //news site view
                    ShimmerEffect(
                        modifier = Modifier
                            .width(80.dp)
                            .height(10.dp)
                    )
                }
            }
        }
    }
}