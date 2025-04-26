package com.vipint.jetpack_compose_playground.sideeffects

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.sideeffects.ui.theme.JetpackComposePlaygroundTheme
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * snapshotFlow converts compose state into Flow
 * use snapshotFlow to convert normal State<T> into a cold flow
 *
 * snapshotFlow runs it block when collected and emits the result of the state objects read in it.
 *
 * When one of the state object read inside the snapshotFlow block mutates, the flow will emit the new value to its collector,
 * if the new value is not equal to previous emitted value
 */

class SnapShotFlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(title = stringResource(R.string.snap_shot_flow)) {
                    SnapshotFlowScrollToTopExample()
                }
            }
        }
    }
}


@Composable
fun SnapshotFlowScrollToTopExample(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var isEnabled by remember { mutableStateOf(false) }
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex > 20 }
            .distinctUntilChanged()
            .collect {
                isEnabled = it
                Log.d("SnapshotFlowScrollToTopExample", "collect: $it")
            }
    }

    LazyColumn(state = listState) {
        items((1..100).toList()) {
            Text(
                text = "Item $it", modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
    Button(onClick = {
        scope.launch {
            listState.scrollToItem(0)
        }
    }, enabled = isEnabled, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Scroll to top")
    }
}