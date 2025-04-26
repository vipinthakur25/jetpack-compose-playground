package com.vipint.jetpack_compose_playground.sideeffects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.sideeffects.ui.theme.JetpackComposePlaygroundTheme
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold

class DerivedStateOfActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(
                    title = stringResource(R.string.derived_state_of), content = {
                        DerivedStateScrollExample()
                    }
                )

            }
        }
    }
}

@Composable
fun DerivedStateScrollExample(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val isEnabled by remember { derivedStateOf { listState.firstVisibleItemIndex > 20 } }
    Button(onClick = {
    }, modifier = Modifier.fillMaxWidth(), enabled = isEnabled) {
        Text(text = "Scroll to top")
    }

    LazyColumn(state = listState) {

        items((1..100).toList()) {
            Text(
                text = "item $it",
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

