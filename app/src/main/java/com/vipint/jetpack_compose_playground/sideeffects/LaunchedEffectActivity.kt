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
import androidx.compose.runtime.LaunchedEffect
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

class LaunchedEffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                JetpackComposePlaygroundTheme {
                    TopAppBarScaffold(
                        title = stringResource(R.string.launched_effect),
                        content = {
                            LaunchedEffectExample()
                        })
                }
            }
        }
    }
}

/**
 * Launched effect is used to call the suspend function in the scope of composable
 *
 * when Launched Effects enters in the composition it launches the coroutine. Coroutine get cancelled when Launched effect leaves the composition
 *
 * If the Launched Effect is recomposed with different keys, the existing coroutine get cancelled and new suspend function will be called in the new coroutine
 */
@Composable
fun LaunchedEffectExample(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val toastText = stringResource(R.string.what_are_side_effects)
    LaunchedEffect(key1 = true) {
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
    }
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