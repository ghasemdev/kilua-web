package dev.kilua.compose.foundation.layout

import dev.kilua.compose.ui.Alignment
import dev.kilua.html.CssSize

/**
 * Used to specify the arrangement of the layout's children in layouts like
 * [Row] or [Column] in their main axis direction (horizontal and vertical,
 * respectively).
 */
object Arrangement {
    /**
     * Base class for arrangement values that makes sense in a horizontal direction.
     */
    sealed interface Horizontal

    /**
     * Base class for arrangement values that makes sense in a vertical direction.
     */
    sealed interface Vertical

    /**
     * Base class for arrangement values that can be passed in as either horizontal or vertical arrangement parameters.
     */
    sealed interface HorizontalOrVertical : Horizontal, Vertical

    data object End : Horizontal
    data object Start : Horizontal
    data object Top : Vertical
    data object Bottom : Vertical
    data object Center : HorizontalOrVertical
    data object SpaceEvenly : HorizontalOrVertical
    data object SpaceBetween : HorizontalOrVertical
    data object SpaceAround : HorizontalOrVertical

    /**
     * Arranges the children of the container with a fixed [space] for both horizontal and vertical orientations.
     * This function is marked as stable, ensuring that its result can be safely used in recompositions.
     *
     * **Important: As we use the CSS `gap` property to apply the spacing, negative spaces will be ignored.**
     *
     * **Usage**:
     * ```kotlin
     * Column(
     *     verticalArrangement = Arrangement.spacedBy(space = 10.px),
     * ) {
     *     SpanText("1")
     *     SpanText("2")
     *     SpanText("3")
     * }
     * ```
     * The above code will generate the following html:
     * ```html
     * <div class="kobweb-col kobweb-arrange-spaced-by kobweb-arrange-start kobweb-align-start" style="--kobweb-arrange-spaced-by: 10px;">
     *     <span class="silk-span-text">1</span>
     *     <span class="silk-span-text">2</span>
     *     <span class="silk-span-text">3</span>
     * </div>
     * ```
     *
     * or
     *
     * ```kotlin
     * Row(
     *     horizontalArrangement = Arrangement.spacedBy(space = 10.px),
     * ) {
     *     SpanText("1")
     *     SpanText("2")
     *     SpanText("3")
     * }
     * ```
     * The above code will generate the following html:
     * ```html
     * <div class="kobweb-row kobweb-arrange-spaced-by kobweb-arrange-start kobweb-align-top" style="--kobweb-arrange-spaced-by: 10px;">
     *     <span class="silk-span-text">1</span>
     *     <span class="silk-span-text">2</span>
     *     <span class="silk-span-text">3</span>
     * </div>
     * ```
     *
     * @param space The space between adjacent children. If given as a percentage, the value is calculated relative to
     * the size of the container. Expects a value between [[0,∞]].
     */
    fun spacedBy(space: CssSize): HorizontalOrVertical =
        SpacedAligned.HorizontalOrVertical(space)

    /**
     * Arranges the children of the container with a fixed [space] for vertical orientations.
     * An [alignment] can be specified to align the spaced children vertically inside the parent,
     * in case there is height remaining.
     *
     * This function is marked as stable, ensuring that its result can be safely used in recompositions.
     *
     * **Important: As we use the CSS `gap` property to apply the spacing, negative spaces will be ignored.**
     *
     * **Usage**:
     * ```kotlin
     * Column(
     *     verticalArrangement = Arrangement.spacedBy(space = 10.px, alignment = Alignment.CenterVertically)),
     * ) {
     *     SpanText("1")
     *     SpanText("2")
     *     SpanText("3")
     * }
     * ```
     * The above code will generate the following html:
     * ```html
     * <div class="kobweb-col kobweb-arrange-spaced-by kobweb-arrange-center kobweb-align-start" style="--kobweb-arrange-spaced-by: 10px;">
     *     <span class="silk-span-text">1</span>
     *     <span class="silk-span-text">2</span>
     *     <span class="silk-span-text">3</span>
     * </div>
     * ```
     *
     * **Note**:
     * When using:
     *  * [Alignment.CenterVertically], the `kobweb-arrange-center` class is placed together with
     *  `kobweb-arrange-spaced-by`
     *  * [Alignment.Top], the `kobweb-arrange-top` class is placed together with
     *  `kobweb-arrange-spaced-by`
     *  * [Alignment.Bottom], the `kobweb-arrange-bottom` class is placed together with
     *  `kobweb-arrange-spaced-by`
     *
     * @param space The space between adjacent children. If given as a percentage, the value is calculated relative to
     * the size of the container. Expects a value between [[0,∞]].
     * @see Alignment.CenterVertically
     * @see Alignment.Top
     * @see Alignment.Bottom
     */
    fun spacedBy(space: CssSize, alignment: Alignment.Vertical): Vertical =
        SpacedAligned.Vertical(space, alignment)

    /**
     * Arranges the children of the container with a fixed [space] for vertical orientations.
     * An [alignment] can be specified to align the spaced children vertically inside the parent,
     * in case there is width remaining.
     *
     * This function is marked as stable, ensuring that its result can be safely used in recompositions.
     *
     * **Important: As we use the CSS `gap` property to apply the spacing, negative spaces will be ignored.**
     *
     * **Usage**:
     * ```kotlin
     * Row(
     *     horizontalArrangement = Arrangement.spacedBy(space = 10.px, alignment = Alignment.CenterHorizontally),
     * ) {
     *     SpanText("1")
     *     SpanText("2")
     *     SpanText("3")
     * }
     * ```
     * The above code will generate the following html:
     * ```html
     * <div class="kobweb-row kobweb-arrange-spaced-by kobweb-arrange-center kobweb-align-top" style="--kobweb-arrange-spaced-by: 10px;">
     *     <span class="silk-span-text">1</span>
     *     <span class="silk-span-text">2</span>
     *     <span class="silk-span-text">3</span>
     * </div>
     * ```
     *
     * **Note**:
     * When using:
     *  * [Alignment.CenterHorizontally], the `kobweb-arrange-center` class is placed together with
     *  `kobweb-arrange-spaced-by`
     *  * [Alignment.Start], the `kobweb-arrange-start` class is placed together with
     *  `kobweb-arrange-spaced-by`
     *  * [Alignment.End], the `kobweb-arrange-end` class is placed together with
     *  `kobweb-arrange-spaced-by`
     *
     * @param space The space between adjacent children. If given as a percentage, the value is calculated relative to
     * the size of the container. Expects a value between [[0,∞]].
     * @see Alignment.CenterHorizontally
     * @see Alignment.Start
     * @see Alignment.End
     */
    fun spacedBy(space: CssSize, alignment: Alignment.Horizontal): Horizontal =
        SpacedAligned.Horizontal(space, alignment)
}

/** Arrangement with spacing between adjacent children. */
internal sealed class SpacedAligned(
    val spacing: CssSize,
) : Arrangement.HorizontalOrVertical {
    /** Represents a spaced (either horizontally or vertically) and aligned arrangement. */
    class HorizontalOrVertical(
        spacing: CssSize,
    ) : SpacedAligned(spacing)

    /**
     * Represents a vertically spaced and aligned arrangement.
     *
     * @param alignment The vertical alignment used in the arrangement.
     */
    class Vertical(
        spacing: CssSize,
        internal val alignment: Alignment.Vertical,
    ) : SpacedAligned(spacing)

    /**
     * Represents a horizontally spaced and aligned arrangement.
     *
     * @param alignment The horizontal alignment used in the arrangement.
     */
    class Horizontal(
        spacing: CssSize,
        internal val alignment: Alignment.Horizontal,
    ) : SpacedAligned(spacing)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (other::class != this::class) return false
        @Suppress("NAME_SHADOWING") val other = other as SpacedAligned

        return this.spacing == other.spacing && when (this) {
            is Vertical -> this.alignment == (other as Vertical).alignment
            is Horizontal -> this.alignment == (other as Horizontal).alignment
            else -> true
        }
    }

    override fun hashCode(): Int {
        var result = spacing.hashCode()
        when (this) {
            is Vertical -> result += 31 * alignment.hashCode()
            is Horizontal -> result += 31 * alignment.hashCode()
            else -> Unit
        }
        return result
    }
}
