package com.spacepulse.presentation.ui.article_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spacepulse.network_module.domain.models.Article

@Composable
fun ArticleCard(article: Article, onClick: () -> Unit) {

    //card item for showing the minimal info of the article
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            //title view
            Text(
                text = article.title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            //space
            Spacer(modifier = Modifier.height(8.dp))
            //news site view
            Text(text = "source: ${article.newsSite}")

        }

    }
}