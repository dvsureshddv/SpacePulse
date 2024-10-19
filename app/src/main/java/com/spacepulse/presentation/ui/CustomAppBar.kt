@file:OptIn(ExperimentalMaterial3Api::class)

package com.spacepulse.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.spacepulse.R


//common app bar
@Composable
fun CustomAppBar(
    title: String,
    isToShowBackButton: Boolean = false,
    isToShowRefreshIcon : Boolean = false,
    onBackButtonClick: () -> Unit,
    onRefreshClick: () -> Unit,
) {
    // custom appbar
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.primary),
            titleContentColor = White,
        ),
        //app bar title
        title = {
            Text(
                text = title,
                fontSize = 22.sp,
                color = White,
                fontWeight = FontWeight.SemiBold,
            )
        },
        //app bar back action
        navigationIcon = {
            if (isToShowBackButton) {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = White
                    )
                }
            }
        },
        //app bar actions
        actions = {
            if(isToShowRefreshIcon) {
                IconButton(onClick = onRefreshClick) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        tint = White
                    )
                }
            }
        }
    )
}