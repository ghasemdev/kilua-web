package dev.kilua.compose.style

import dev.kilua.compose.ui.Alignment

fun Alignment.toClassNameSelf(): String = "${toClassName()}-self"

fun Alignment.toClassName(): String = when (this) {
    Alignment.TopStart -> "kilua-align-top-start"
    Alignment.TopCenter -> "kilua-align-top-center"
    Alignment.TopEnd -> "kilua-align-top-end"
    Alignment.CenterStart -> "kilua-align-center-start"
    Alignment.Center -> "kilua-align-center"
    Alignment.CenterEnd -> "kilua-align-center-end"
    Alignment.BottomStart -> "kilua-align-bottom-start"
    Alignment.BottomCenter -> "kilua-align-bottom-center"
    Alignment.BottomEnd -> "kilua-align-bottom-end"
}

fun Alignment.Vertical.toClassName(): String = when (this) {
    Alignment.Top -> "kilua-align-top"
    Alignment.CenterVertically -> "kilua-align-center-vertical"
    Alignment.Bottom -> "kilua-align-bottom"
}

fun Alignment.Horizontal.toClassName(): String = when (this) {
    Alignment.Start -> "kilua-align-start"
    Alignment.CenterHorizontally -> "kilua-align-center-horizontal"
    Alignment.End -> "kilua-align-end"
}
