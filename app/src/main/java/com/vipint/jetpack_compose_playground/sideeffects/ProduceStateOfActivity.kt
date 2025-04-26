package com.vipint.jetpack_compose_playground.sideeffects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.sideeffects.ui.theme.JetpackComposePlaygroundTheme
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * produceStateOf converts the non-compose state into compose state\
 *
 *  @{produceStateOf} launches a coroutine scope within the composable that can push the value in the return state
 *
 *  we can use it when we are dealing with subscription based state such as LiveData, FLow or RxJava
 *
 *  LifeCycle-Aware-Execution: produceStateOf ensures that asynchronous operation are cancelled when the composable removed from the composition, preventing potential
 *  memory leak and ensuring resource efficiency
 *
 *  Seamless States Update : It enables the state within the composable functions to be updated reactively upon the completion of asynchronous task, triggering recomposition with the new data
 */
class ProduceStateOfActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(
                    title = stringResource(R.string.produce_state_of)
                ) {
                   // ProduceStateExample(url = "Someurl")
                    PostListScreen()
                }

            }
        }
    }
}

@Composable
fun ProduceStateExample(modifier: Modifier = Modifier, url: String) {
    val networkResult = loadDataFromNetwork(url)

    when (networkResult.value) {
        is NetworkResult.Success -> {
            ShowText((networkResult.value as NetworkResult.Success).data)
        }

        is NetworkResult.Loading -> {
            ShowText("Loading...")
        }

        is NetworkResult.Error -> {
            ShowText("Error")
        }
    }
}

@Composable
fun ShowText(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}

@Composable
fun loadDataFromNetwork(
    url: String, networkService: NetworkService = NetworkService()
): State<NetworkResult> {
    return produceState<NetworkResult>(
        initialValue = NetworkResult.Loading, url, networkService
    ) {

        val data = networkService.fetch(url)

        value = if (data.isEmpty()) {
            NetworkResult.Error
        } else {
            NetworkResult.Success(data)
        }
    }
}

sealed interface NetworkResult {

    data class Success(val data: String) : NetworkResult

    object Error : NetworkResult

    object Loading : NetworkResult

}


class NetworkService {
    suspend fun fetch(url: String): String {
        return withContext(Dispatchers.IO) {
            delay(3000L)
            return@withContext "Data from Network"
        }
    }

}

