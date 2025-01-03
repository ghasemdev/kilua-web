package dev.kilua.compose.style

import androidx.compose.runtime.Composable
import dev.kilua.compose.ui.Alignment
import dev.kilua.html.AlignItems
import dev.kilua.html.ITag
import dev.kilua.html.JustifyItems
import web.dom.HTMLElement

@Composable
fun <T : HTMLElement> ITag<T>.alignmentToStyle(contentAlignment: Alignment) {
    when (contentAlignment) {
        Alignment.TopStart -> {
            alignItems(AlignItems.Start)
            justifyItems(JustifyItems.Start)
        }

        Alignment.TopCenter -> {
            alignItems(AlignItems.Start)
            justifyItems(JustifyItems.Center)
        }

        Alignment.TopEnd -> {
            alignItems(AlignItems.Start)
            justifyItems(JustifyItems.End)
        }

        Alignment.CenterStart -> {
            alignItems(AlignItems.Center)
            justifyItems(JustifyItems.Start)
        }

        Alignment.Center -> {
            alignItems(AlignItems.Center)
            justifyItems(JustifyItems.Center)
        }

        Alignment.CenterEnd -> {
            alignItems(AlignItems.Center)
            justifyItems(JustifyItems.End)
        }

        Alignment.BottomStart -> {
            alignItems(AlignItems.End)
            justifyItems(JustifyItems.Start)
        }

        Alignment.BottomCenter -> {
            alignItems(AlignItems.End)
            justifyItems(JustifyItems.Center)
        }

        Alignment.BottomEnd -> {
            alignItems(AlignItems.End)
            justifyItems(JustifyItems.End)
        }

        Alignment.FromStyle -> {}
    }
}

@Composable
fun <T : HTMLElement> ITag<T>.alignmentToStyle(contentAlignment: Alignment.Vertical) {
    when (contentAlignment) {
        Alignment.Top -> alignItems(AlignItems.Start)
        Alignment.CenterVertically -> alignItems(AlignItems.Center)
        Alignment.Bottom -> alignItems(AlignItems.End)
        Alignment.FromStyle -> {}
    }
}

@Composable
fun <T : HTMLElement> ITag<T>.alignmentToStyle(contentAlignment: Alignment.Horizontal) {
    when (contentAlignment) {
        Alignment.Start -> justifyItems(JustifyItems.Start)
        Alignment.CenterHorizontally -> justifyItems(JustifyItems.Center)
        Alignment.End -> justifyItems(JustifyItems.End)
        Alignment.FromStyle -> {}
    }
}
