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
 * Here we are using the concept of State Hoisting
 * We hoist the counter state in parent calling composable and we pass state as parameter in child composable
 *
 * by this we make composable reusable and somewhere testing easy
 */
class StateHoistingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(title = stringResource(R.string.state_hoisting)) {
                    StateHoistingExample()
                }
            }
        }
    }
}

@Preview
@Composable
fun StateHoistingExample(modifier: Modifier = Modifier) {
    var counter by remember { mutableIntStateOf(0) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            StateHoistingButtonContent(counter = {
                counter++
            })
            StateHoistingTextContent(counter = counter)
        })
}

@Composable
fun StateHoistingButtonContent(modifier: Modifier = Modifier, counter: () -> Unit) {

    Button(onClick = {
        counter()
    }) {
        Text(stringResource(R.string.click_here))
    }
}

@Composable
fun StateHoistingTextContent(modifier: Modifier = Modifier, counter: Int) {
    Text(text = "Counter $counter", modifier = Modifier.padding(20.dp))
}