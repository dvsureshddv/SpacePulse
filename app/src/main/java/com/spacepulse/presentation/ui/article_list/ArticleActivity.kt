package com.spacepulse.presentation.ui.article_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.spacepulse.R
import com.spacepulse.navigation.SpacePulseNavHost
import com.spacepulse.presentation.theme.SpacePulseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set the status bar color
        window.statusBarColor = getColor(R.color.primary)
        setContent {
            SpacePulseTheme {
                setContent {
                    SpacePulseNavHost()
                }
            }
        }
    }
}