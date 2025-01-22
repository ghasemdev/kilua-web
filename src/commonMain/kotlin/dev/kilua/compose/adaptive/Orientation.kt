package dev.kilua.compose.adaptive

import androidx.compose.runtime.*

enum class Orientation {
    PORTRAIT,
    LANDSCAPE,
}

@Composable
fun rememberOrientation(): State<Orientation> {
    val isPortrait by rememberMediaQueryAsState("(orientation: portrait)")

    return remember {
        derivedStateOf {
            when {
                isPortrait -> Orientation.PORTRAIT
                else -> Orientation.LANDSCAPE
            }
        }
    }
}
