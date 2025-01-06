package dev.kilua.compose.ui

sealed interface Alignment {
    sealed interface Vertical
    sealed interface Horizontal

    data object TopStart : Alignment
    data object TopCenter : Alignment
    data object TopEnd : Alignment
    data object CenterStart : Alignment
    data object Center : Alignment
    data object CenterEnd : Alignment
    data object BottomStart : Alignment
    data object BottomCenter : Alignment
    data object BottomEnd : Alignment

    data object Top : Vertical
    data object CenterVertically : Vertical
    data object Bottom : Vertical

    data object Start : Horizontal
    data object CenterHorizontally : Horizontal
    data object End : Horizontal
}
