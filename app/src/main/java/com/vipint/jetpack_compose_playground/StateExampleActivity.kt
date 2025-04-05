package com.vipint.jetpack_compose_playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold
import com.vipint.jetpack_compose_playground.ui.theme.JetpackComposePlaygroundTheme

class StateExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(title = getString(R.string.state_example), content = {
                    Column(modifier = Modifier.padding(10.dp)) {
                        TextFieldWithoutState()
                        TextFiledWithNormalVariable()
                        TextFieldWithState()
                        StateFulCounter()
                        var count by remember { mutableIntStateOf(0) }
                        StatelessCounter(onClick = {
                            count++
                        }, count = count)
                    }
                })
            }
        }
    }

    @Composable
    fun TextFieldWithoutState(modifier: Modifier = Modifier) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            value = "",
            onValueChange = { },
            label = { Text(getString(R.string.first_name)) })
    }


    @Composable
    fun TextFiledWithNormalVariable(modifier: Modifier = Modifier) {
        var lastName = ""
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(getString(R.string.last_name)) })
    }

    @Composable
    fun TextFieldWithState(modifier: Modifier = Modifier) {
        var userName by remember { mutableStateOf("") }
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            value = userName,
            onValueChange = {
                userName = it
            },
            label = { Text(getString(R.string.user_name)) })
    }

    /**
     * Stateful Composable, here we are managing the state inside the composable
     */

    @Composable
    fun StateFulCounter(modifier: Modifier = Modifier) {
        var counter by remember { mutableIntStateOf(0) }
        Button(onClick = { counter++ }) {
            Text("Clicked $counter times")
        }
    }

    /**
     * Stateless Composable, here we managing the state outside the composable
     */
    @Composable
    fun StatelessCounter(modifier: Modifier = Modifier, count: Int, onClick: () -> Unit) {
        Button(onClick = onClick) {
            Text("Clicked $count times")
        }
    }
}
