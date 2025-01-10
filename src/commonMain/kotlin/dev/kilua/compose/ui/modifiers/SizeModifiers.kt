package dev.kilua.compose.ui.modifiers

import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.styleModifier
import dev.kilua.compose.web.css.height
import dev.kilua.compose.web.css.width
import dev.kilua.html.CssSize

fun Modifier.width(width: CssSize): Modifier = styleModifier {
    width(width)
}

fun Modifier.height(height: CssSize): Modifier = styleModifier {
    height(height)
}
