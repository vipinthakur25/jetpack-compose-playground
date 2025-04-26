package com.vipint.jetpack_compose_playground.sideeffects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.sideeffects.ui.theme.JetpackComposePlaygroundTheme
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold
import kotlinx.coroutines.launch

class RememberCoroutineScopeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(
                    title = stringResource(R.string.remember_coroutine_scope),
                    content = {
                        RememberCoroutineScopeExample()
                    })
            }
        }
    }
}

/**
 * rememberCoroutineScope is also a way to handle Side Effects in a predicated manner.
 * Like Launched Effect rememberCoroutineScope is also used to run suspend function.
 *
 * But we have some limitation in Launched Effect like we can call it from a Composable function only
 *
 * So to launch a coroutine outside of the composable but in composition aware scope we use rememberCoroutineScope
 *
 * In order to launch a coroutine outside of the composable but scoped so that it will get automatically cancelled whenever it leaves the composition we use rememberCoroutineScope,
 * also use rememberCoroutineScope when you need to control the lifecycle of one or more coroutines manually
 * rememberCoroutineScope is a composable function which returns a coroutineScope bound to the point of composition where it's called. The scope will be cancelled when it leaves the composition.
 *
 */
@Composable
fun RememberCoroutineScopeExample(modifier: Modifier = Modifier) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val snackBarText = stringResource(R.string.remember_coroutine_scope)
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                scope.launch {
                    snackBarHostState.showSnackbar(message = snackBarText)
                }
            }) {
                Text(stringResource(R.string.show_snackbar))
            }
        }
    }
}