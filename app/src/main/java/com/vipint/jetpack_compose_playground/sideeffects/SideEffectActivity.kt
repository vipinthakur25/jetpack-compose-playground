package com.vipint.jetpack_compose_playground.sideeffects

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.sideeffects.ui.theme.JetpackComposePlaygroundTheme
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold

class SideEffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(title = stringResource(R.string.side_effect), content = {
                    SideEffectExample()
                })
            }
        }
    }
}

/**
 * SideEffect guarantees it execute on every successful recomposition
 */
@Composable
fun SideEffectExample(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    var compositionCounter = remember { CompositionCounter(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            counter++
        }) {
            Text(stringResource(R.string.click_here))
        }
        Text("$counter", modifier = Modifier.padding(20.dp))
    }
    SideEffect {
        compositionCounter.count++
        Log.d("SideEffectExample", "SideEffect block compositionCounter : $compositionCounter")

    }
    Log.d("SideEffectExample", "Recomposition")
}

data class CompositionCounter(var count: Int)