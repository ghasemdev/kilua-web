package dev.kilua.compose.web.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.kilua.html.helpers.TagEvents
import web.dom.HTMLElement

@Composable
fun <E : HTMLElement> TagEvents<E>.rememberIsHoveredAsState(): State<Boolean> {
    val state = remember { mutableStateOf(false) }
    onMouseOver { state.value = true }
    onMouseOut { state.value = false }
    return state
}
