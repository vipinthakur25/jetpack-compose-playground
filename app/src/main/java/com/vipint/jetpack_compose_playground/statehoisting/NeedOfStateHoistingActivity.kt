package com.vipint.jetpack_compose_playground.statehoisting

import android.os.Bundle
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.statehoisting.ui.theme.JetpackComposePlaygroundTheme
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold

/**
 * so in this example we have to show counter in Text when we click the Button
 * But here is one problem we don't have access to counter state in Text
 *
 * And here the need of State hoisting comes into the picture,
 * to hoist the state in parent composable so we make child composable independent to state
 * by this we make composable reusable and somewhere testing easy
 */
class NeedOfStateHoistingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(title = stringResource(R.string.need_of_state_hoisitng)) {
                    NeedOfStateHoistingExample()
                }
            }
        }
    }
}

@Preview
@Composable
fun NeedOfStateHoistingExample(modifier: Modifier = Modifier) {

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            NeedOfStateHoistingButtonContent()
            NeedOfStateHoistingTextContent()
        })
}

@Composable
fun NeedOfStateHoistingButtonContent(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    Button(onClick = {
        counter++
    }) {
        Text(stringResource(R.string.click_here))
    }
}

@Composable
fun NeedOfStateHoistingTextContent(modifier: Modifier = Modifier) {
    Text(text = "Show counter here", modifier = Modifier.padding(20.dp))
}