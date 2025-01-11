package dev.kilua.compose.web.css

import dev.kilua.html.Background

fun StyleScope.background(background: Background) {
    property("background", background.toString())
}
