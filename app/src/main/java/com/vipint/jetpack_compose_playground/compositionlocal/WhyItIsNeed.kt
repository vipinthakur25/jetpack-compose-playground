package com.vipint.jetpack_compose_playground.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

data class Colors(val primary: Color = Color.Blue, val secondary: Color = Color.Gray)

/**
 * The problem here is that we have to pass color has parameter in every composable again and again
 *
 * So this is some kind of styling and we have to centralize this
 *
 */
@Preview
@Composable
fun WhyItIsNeeded(modifier: Modifier = Modifier) {
    val colors = Colors()
    Column {
        TextOne(displayText = "Text One", colors = colors)
        DisplayOtherTexts(colors = colors)
    }
}

@Composable
fun DisplayOtherTexts(colors: Colors) {
    TextTwo(displayText = "TextTwo", colors = colors)
    TextThree(displayText = "TextThree", colors = colors)
}

@Composable
fun TextOne(modifier: Modifier = Modifier, displayText: String, colors: Colors) {
    Text(
        text = displayText,
        color = colors.primary
    )
}

@Composable
fun TextTwo(modifier: Modifier = Modifier, displayText: String, colors: Colors) {
    Text(
        text = displayText,
        color = colors.secondary
    )
}

@Composable
fun TextThree(modifier: Modifier = Modifier, displayText: String, colors: Colors) {
    Text(
        text = displayText,
        color = colors.secondary
    )
}