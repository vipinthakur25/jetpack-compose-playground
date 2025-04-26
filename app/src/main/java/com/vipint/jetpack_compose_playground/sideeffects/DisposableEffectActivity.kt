package com.vipint.jetpack_compose_playground.sideeffects

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vipint.jetpack_compose_playground.R
import com.vipint.jetpack_compose_playground.sideeffects.ui.theme.JetpackComposePlaygroundTheme
import com.vipint.jetpack_compose_playground.ui.common.TopAppBarScaffold

class DisposableEffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePlaygroundTheme {
                TopAppBarScaffold(title = stringResource(R.string.disposable_effect), content = {
                    TestingTestField()
                    DisposableEffectExample()
                })
            }
        }
    }
}


@Composable
fun TestingTestField(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        TextField(value = input, onValueChange = { input = it })
    }
}

/**
 * we use DisposableEffect when we need to clean up the the side effect
 * for the sideeffects that needs to be cleanup when disposable key changes or it leaves the composition, use  DisposableEffect
 *
 * if the DisposableEffect key changes, the composable needs to be dispose(do clean up) its current effect, and reset by calling the effect again
 *
 * and we can call non suspend thing in it
 */

@Composable
fun DisposableEffectExample(modifier: Modifier = Modifier) {
    val view = LocalView.current
    val context = LocalContext.current
    var toastString = ""
    DisposableEffect(key1 = Unit) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(view)
            val isKeyBoardVisible = insets?.isVisible(WindowInsetsCompat.Type.ime())
            toastString =
                if (isKeyBoardVisible == true) "Keyboard is open" else "keyboard is closed"
            Toast.makeText(context, toastString, Toast.LENGTH_SHORT).show()
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}

