package dev.kilua.compose.style

import androidx.compose.runtime.Composable
import dev.kilua.compose.ui.Alignment
import dev.kilua.html.AlignItems
import dev.kilua.html.ITag
import dev.kilua.html.JustifyItems
import web.dom.HTMLElement

@Composable
fun <T : HTMLElement> ITag<T>.selfAlignmentToStyle(alignment: Alignment) {
    when (alignment) {
        Alignment.TopStart -> {
            alignSelf(AlignItems.Start)
            justifySelf(JustifyItems.Start)
        }

        Alignment.TopCenter -> {
            alignSelf(AlignItems.Start)
            justifySelf(JustifyItems.Center)
        }

        Alignment.TopEnd -> {
            alignSelf(AlignItems.Start)
            justifySelf(JustifyItems.End)
        }

        Alignment.CenterStart -> {
            alignSelf(AlignItems.Center)
            justifySelf(JustifyItems.Start)
        }

        Alignment.Center -> {
            alignSelf(AlignItems.Center)
            justifySelf(JustifyItems.Center)
        }

        Alignment.CenterEnd -> {
            alignSelf(AlignItems.Center)
            justifySelf(JustifyItems.End)
        }

        Alignment.BottomStart -> {
            alignSelf(AlignItems.End)
            justifySelf(JustifyItems.Start)
        }

        Alignment.BottomCenter -> {
            alignSelf(AlignItems.End)
            justifySelf(JustifyItems.Center)
        }

        Alignment.BottomEnd -> {
            alignSelf(AlignItems.End)
            justifySelf(JustifyItems.End)
        }

        Alignment.FromStyle -> {}
    }
}

@Composable
fun <T : HTMLElement> ITag<T>.alignmentToStyle(alignment: Alignment) {
    when (alignment) {
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
