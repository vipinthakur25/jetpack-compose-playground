package com.vipint.jetpack_compose_playground.compositionlocal

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun AccessContext() {
    val context = LocalContext.current
}