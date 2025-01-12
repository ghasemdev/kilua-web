package dev.kilua.compose.style

import dev.kilua.compose.ui.Alignment
import dev.kilua.compose.ui.Arrangement
import dev.kilua.compose.ui.SpacedAligned
import dev.kilua.compose.ui.SpacedAligned.Horizontal
import dev.kilua.compose.ui.SpacedAligned.Vertical

internal const val KILUA_ARRANGE_BOTTOM = "kilua-arrange-bottom"
internal const val KILUA_ARRANGE_CENTER = "kilua-arrange-center"
internal const val KILUA_ARRANGE_END = "kilua-arrange-end"
internal const val KILUA_ARRANGE_SPACED_BY = "kilua-arrange-spaced-by"
internal const val KILUA_ARRANGE_SPACE_AROUND = "kilua-arrange-space-around"
internal const val KILUA_ARRANGE_SPACE_BETWEEN = "kilua-arrange-space-between"
internal const val KILUA_ARRANGE_SPACE_EVENLY = "kilua-arrange-space-evenly"
internal const val KILUA_ARRANGE_START = "kilua-arrange-start"
internal const val KILUA_ARRANGE_TOP = "kilua-arrange-top"

private val SpacedAligned.alignmentClassName: String
    get() = when (this) {
        is Vertical -> when (alignment) {
            Alignment.Bottom -> KILUA_ARRANGE_BOTTOM
            Alignment.CenterVertically -> KILUA_ARRANGE_CENTER
            Alignment.Top -> KILUA_ARRANGE_TOP
        }

        is Horizontal -> when (alignment) {
            Alignment.Start -> KILUA_ARRANGE_START
            Alignment.CenterHorizontally -> KILUA_ARRANGE_CENTER
            Alignment.End -> KILUA_ARRANGE_END
        }

        else -> KILUA_ARRANGE_START
    }

fun Arrangement.Horizontal.toClassNames(): Array<String> = when (this) {
    Arrangement.End -> arrayOf(KILUA_ARRANGE_END)
    Arrangement.Start -> arrayOf(KILUA_ARRANGE_START)
    is Arrangement.HorizontalOrVertical -> toClassNames()
}

fun Arrangement.Vertical.toClassNames(): Array<String> = when (this) {
    Arrangement.Top -> arrayOf(KILUA_ARRANGE_TOP)
    Arrangement.Bottom -> arrayOf(KILUA_ARRANGE_BOTTOM)
    is Arrangement.HorizontalOrVertical -> toClassNames()
}

fun Arrangement.HorizontalOrVertical.toClassNames(): Array<String> = when (this) {
    Arrangement.Center -> arrayOf(KILUA_ARRANGE_CENTER)
    Arrangement.SpaceAround -> arrayOf(KILUA_ARRANGE_SPACE_AROUND)
    Arrangement.SpaceBetween -> arrayOf(KILUA_ARRANGE_SPACE_BETWEEN)
    Arrangement.SpaceEvenly -> arrayOf(KILUA_ARRANGE_SPACE_EVENLY)
    is SpacedAligned -> arrayOf(KILUA_ARRANGE_SPACED_BY, alignmentClassName)
}
