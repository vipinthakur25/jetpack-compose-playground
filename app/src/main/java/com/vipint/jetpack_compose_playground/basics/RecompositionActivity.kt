package com.vipint.jetpack_compose_playground.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold
import com.vipint.jetpack_compose_playground.ui.theme.JetpackComposePlaygroundTheme

class RecompositionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(
                    title = stringResource(R.string.recomposition_example),
                    content = {
                        Column {
                            RecompositionExample()
                        }
                    })
            }
        }
    }


    @Composable
    fun RecompositionExample(modifier: Modifier = Modifier) {
        var count by remember { mutableIntStateOf(0) }

        Button(onClick = {
            count++
        }, content = {
            Text("Count: $count")
        })

    }
}
