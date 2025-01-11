package dev.kilua.compose.ui.modifiers

import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.styleModifier
import dev.kilua.compose.web.css.background
import dev.kilua.html.*

fun Modifier.background(background: Background) = styleModifier {
    background(background)
}

fun Modifier.background(
    color: Color? = null,
    image: String? = null,
    positionX: CssSize? = null,
    positionY: CssSize? = null,
    sizeX: CssSize? = null,
    sizeY: CssSize? = null,
    size: BgSize? = null,
    repeat: BgRepeat? = null,
    origin: BgOrigin? = null,
    clip: BgClip? = null,
    attachment: BgAttach? = null
) = background(
    Background(
        color = color,
        image = image,
        positionX = positionX,
        positionY = positionY,
        sizeX = sizeX,
        sizeY = sizeY,
        size = size,
        repeat = repeat,
        origin = origin,
        clip = clip,
        attachment = attachment
    )
)
