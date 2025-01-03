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

    /**
     * A special value indicating that this element's alignment will be controlled manually using CSS styles.
     *
     * For example:
     *
     * ```
     * // We want to use CssStyle + breakpoints to control the layout of our row
     * val ResponsiveStyle = CssStyle {
     *   base { Modifier.alignItems(Top) }
     *   Breakpoint.MD { Modifier.alignItems(Center) }
     * }
     *
     * /* ... later ... */
     * Row(ResponsiveStyle.toModifier(), verticalAlignment = Alignment.FromStyle)
     * ```
     *
     * Using this means you know what you are doing! And that you understand which display type is powering the
     * underlying element (grid for boxes, flexbox for rows and columns). It will be up to you to use the right
     * `justify` / `align` modifier methods to get the behavior you want.
     */
    data object FromStyle : Alignment, Horizontal, Vertical
}
