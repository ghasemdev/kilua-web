package dev.kilua.compose.web.css

import dev.kilua.html.CssSize

fun StyleScope.width(width: CssSize)  {
    property("width", width.value)
}

fun StyleScope.height(height: CssSize)  {
    property("height", height.value)
}
