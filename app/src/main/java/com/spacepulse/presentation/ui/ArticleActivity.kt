package com.spacepulse.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.spacepulse.R
import com.spacepulse.navigation.APPNavHost
import com.spacepulse.presentation.ui.theme.SpacePulseTheme
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
                    APPNavHost()
                }
            }
        }
    }
}