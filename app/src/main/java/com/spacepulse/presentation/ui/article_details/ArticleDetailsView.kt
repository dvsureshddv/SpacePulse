package com.spacepulse.presentation.ui.article_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.spacepulse.R
import com.spacepulse.presentation.components.CustomAppBar
import com.spacepulse.presentation.components.ShimmerEffect
import com.spacepulse.presentation.view_models.NewsViewModel

@Composable
fun ArticleDetailsView(
    newsViewModel: NewsViewModel,
    navController: NavHostController
) {

    //get the selected article from view model
    val article by newsViewModel.selectedArticle.collectAsState(initial = null)

    Scaffold(
        //app bar
        topBar = {
            CustomAppBar(
                title = stringResource(R.string.article_details),
                isToShowBackButton = true,
                onBackButtonClick = {
                    navController.popBackStack()
                },
                onRefreshClick = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),

            ) {

            //title text
            Text(
                text = article?.title ?: "-",
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
            )

            //spacing
            Spacer(modifier = Modifier.height(8.dp))


            //fetch the image from url
            val imageRequest = ImageRequest.Builder(LocalContext.current)
                .data(article?.imageUrl)
                .crossfade(true)
                .build()

            SubcomposeAsyncImage(
                model = imageRequest,
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                loading = {
                    ShimmerEffect(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                },
                error = {
                    ShimmerEffect(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                }
            )

            //spacing
            Spacer(modifier = Modifier.height(8.dp))

            //summary text
            Text(
                text = "Summary:\n${article?.summary ?: "-"}",
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
        }

    }

}

