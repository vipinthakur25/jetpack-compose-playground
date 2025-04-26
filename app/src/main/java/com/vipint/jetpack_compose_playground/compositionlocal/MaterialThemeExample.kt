package com.vipint.jetpack_compose_playground.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * so here we are using MaterialTheme which is given by compose so it is centralize way
 */
@Preview
@Composable
fun MaterialThemeExample() {

    MaterialTheme {
        Column {
            MaterialTextOne(displayText = "TextOne")
            MaterialDisplayOtherTexts()
        }
    }

}

@Composable
fun MaterialDisplayOtherTexts() {
    MaterialTextTwo(displayText = "TextTwo")
    MaterialTextThree(displayText = "TextThree")
}

@Composable
fun MaterialTextOne(displayText: String) {
    Text(
        text = displayText,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun MaterialTextTwo(displayText: String) {
    Text(
        text = displayText,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun MaterialTextThree(displayText: String) {
    Text(
        text = displayText,
        color = MaterialTheme.colorScheme.secondary
    )
}