package com.spacepulse.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spacepulse.presentation.theme.PrimaryColor


@Composable
fun LoadingIndicator(modifier: Modifier = Modifier,
                     alignment: Alignment,
                     color : Color = PrimaryColor
) {

    //loading indicator
    Box(
        modifier = modifier,
        contentAlignment = alignment
    ) {
        CircularProgressIndicator(
            color = color,
            modifier = Modifier.size(48.dp)
        )
    }
}