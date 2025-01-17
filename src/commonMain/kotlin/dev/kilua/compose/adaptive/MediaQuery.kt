package dev.kilua.compose.adaptive

import androidx.compose.runtime.*
import web.window

@Composable
fun rememberMediaQueryAsState(query: String): State<Boolean> {
    val mediaQuery = remember(query) { window.matchMedia(query) }
    val matches = remember { mutableStateOf(mediaQuery.matches) }
    LaunchedEffect(Unit) {
        mediaQuery.onchange = {
            matches.value = mediaQuery.matches
        }
    }
    return matches
}
