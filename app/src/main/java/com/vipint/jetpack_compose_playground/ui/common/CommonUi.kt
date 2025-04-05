package com.vipint.jetpack_compose_playground.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScaffold(
    modifier: Modifier = Modifier, title: String, content: @Composable () -> Unit = {}
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(title) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary, titleContentColor = Color.White
            )
        )
    }, content = {
        Surface(
            modifier = Modifier.padding(it), color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    })
}