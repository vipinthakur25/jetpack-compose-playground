package com.vipint.jetpack_compose_playground.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * What is the role of CompositionLocal in Jetpack Compose?
 *
 * CompositionLocal in Jetpack Compose is used to provide
 * values that can be accessed by the Composable functions
 * in the composition tree. It allows you to pass down values,
 * such as themes, fonts, or other contextual information,
 * without explicitly passing them through function parameters.
 *
 */

data class MyColors(val primary: Color = Color.Blue, val secondary: Color = Color.Gray)

val LocalMyColors = compositionLocalOf {
    MyColors()
}

@Preview
@Composable
fun ConsumeCompositionLocal() {
    Column {
        ConsumeCompositionLocalTextOne(displayText = "TextOne")
        ConsumeCompositionLocalDisplayOtherTexts()
    }
}

@Composable
fun ConsumeCompositionLocalDisplayOtherTexts() {
    ConsumeCompositionLocalTextTwo(displayText = "TextTwo")
    ConsumeCompositionLocalTextThree(displayText = "TextThree")
}

@Composable
fun ConsumeCompositionLocalTextOne(modifier: Modifier = Modifier, displayText: String) {
    Text(text = displayText, color = LocalMyColors.current.primary)
}

@Composable
fun ConsumeCompositionLocalTextTwo(modifier: Modifier = Modifier, displayText: String) {
    Text(text = displayText, color = LocalMyColors.current.secondary)
}

@Composable
fun ConsumeCompositionLocalTextThree(modifier: Modifier = Modifier, displayText: String) {
    Text(text = displayText, color = LocalMyColors.current.secondary)
}