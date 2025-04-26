package com.vipint.jetpack_compose_playground.sideeffects

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.sideeffects.ui.theme.JetpackComposePlaygroundTheme
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold

class WhatAreSideEffectsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(
                    title = stringResource(R.string.what_are_side_effects),
                    content = {
                        WhatAreSideEffects()
                    })
            }
        }
    }
}

/**
 * So here when recomposition happens like when counter value is incremented this composable function get called again and toast shown again when recomposition
 * happens toast msg shown again and again so this is side effect
 * so side effect is an change in sate of the app that happens outside of the scope of the composable
 * to overcome this we can use many things like LaunchedEffect, rememberCoroutineScope, Disposable effect etc
 */
@Composable
fun WhatAreSideEffects(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val toastText = stringResource(R.string.what_are_side_effects)
    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { counter++ }) {
            Text(stringResource(R.string.click_here))
        }
        Text(
            "$counter", modifier =
            Modifier.padding(20.dp)
        )
    }
}
