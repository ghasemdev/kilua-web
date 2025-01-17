@file:Suppress("MemberVisibilityCanBePrivate")

package dev.kilua.compose.adaptive

import androidx.compose.runtime.Immutable
import dev.kilua.compose.adaptive.WindowWidthSizeClass.*
import dev.kilua.compose.ui.geometry.Size
import dev.kilua.compose.ui.util.fastForEach

/**
 * Window size classes are a set of opinionated viewport breakpoints to design, develop, and test
 * responsive application layouts against. For more details check <a
 * href="https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes"
 * class="external" target="_blank">Support different screen sizes</a> documentation.
 *
 * WindowSizeClass contains a [WindowWidthSizeClass] and [WindowHeightSizeClass], representing the
 * window size classes for this window's width and height respectively.
 *
 * See [calculateWindowSizeClass] to calculate the WindowSizeClass
 *
 * @property widthSizeClass width-based window size class ([WindowWidthSizeClass])
 * @property heightSizeClass height-based window size class ([WindowHeightSizeClass])
 */
@Immutable
class WindowSizeClass private constructor(
    val widthSizeClass: WindowWidthSizeClass,
    val heightSizeClass: WindowHeightSizeClass
) {
    companion object {
        /**
         * Calculates the best matched [WindowSizeClass] for a given [size] according to the
         * provided [supportedWidthSizeClasses] and [supportedHeightSizeClasses].
         *
         * @param size of the window
         * @param supportedWidthSizeClasses the set of width size classes that are supported
         * @param supportedHeightSizeClasses the set of height size classes that are supported
         * @return [WindowSizeClass] corresponding to the given width and height
         */
        fun calculateFromSize(
            size: Size,
            supportedWidthSizeClasses: Set<WindowWidthSizeClass> =
                WindowWidthSizeClass.DefaultSizeClasses,
            supportedHeightSizeClasses: Set<WindowHeightSizeClass> =
                WindowHeightSizeClass.DefaultSizeClasses
        ): WindowSizeClass {
            val windowWidthSizeClass =
                WindowWidthSizeClass.fromWidth(size.width.toInt(), supportedWidthSizeClasses)
            val windowHeightSizeClass =
                WindowHeightSizeClass.fromHeight(size.height.toInt(), supportedHeightSizeClasses)
            return WindowSizeClass(windowWidthSizeClass, windowHeightSizeClass)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WindowSizeClass

        if (widthSizeClass != other.widthSizeClass) return false
        if (heightSizeClass != other.heightSizeClass) return false

        return true
    }

    override fun hashCode(): Int {
        var result = widthSizeClass.hashCode()
        result = 31 * result + heightSizeClass.hashCode()
        return result
    }

    override fun toString() = "WindowSizeClass($widthSizeClass, $heightSizeClass)"
}

/**
 * Width-based window size class.
 *
 * A window size class represents a breakpoint that can be used to build responsive layouts. Each
 * window size class breakpoint represents a majority case for typical device scenarios so your
 * layouts will work well on most devices and configurations.
 *
 * For more details see <a
 * href="https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#window_size_classes"
 * class="external" target="_blank">Window size classes documentation</a>.
 */
@Immutable
sealed class WindowWidthSizeClass : Comparable<WindowWidthSizeClass> {
    /** Represents the majority of phones in portrait. */
    data object Compact : WindowWidthSizeClass()

    /** Represents the majority of tablets in portrait and large unfolded inner displays in portrait. */
    data object Medium : WindowWidthSizeClass()

    /** Represents the majority of tablets in landscape and large unfolded inner displays in landscape. */
    data object Expanded : WindowWidthSizeClass()

    override operator fun compareTo(other: WindowWidthSizeClass) =
        breakpoint().compareTo(other.breakpoint())

    override fun toString(): String = "WindowWidthSizeClass." + when (this) {
        Compact -> "Compact"
        Medium -> "Medium"
        Expanded -> "Expanded"
    }

    companion object {
        var COMPACT_PX = 0
        var MEDIUM_PX = 600
        var EXPANDED_PX = 840

        /**
         * The default set of size classes that includes [Compact], [Medium], and [Expanded] size
         * classes. Should never expand to ensure behavioral consistency.
         */
        @Suppress("PrimitiveInCollection")
        val DefaultSizeClasses = setOf(Compact, Medium, Expanded)

        @Suppress("PrimitiveInCollection")
        private val AllSizeClassList = listOf(Expanded, Medium, Compact)

        /**
         * The set of all size classes. It's supposed to be expanded whenever a new size class is
         * defined. By default [WindowSizeClass.calculateFromSize] will only return size classes in
         * [DefaultSizeClasses] in order to avoid behavioral changes when new size classes are
         * added. You can opt in to support all available size classes by doing:
         * ```
         * WindowSizeClass.calculateFromSize(
         *     size = size,
         *     density = density,
         *     supportedWidthSizeClasses = WindowWidthSizeClass.AllSizeClasses,
         *     supportedHeightSizeClasses = WindowHeightSizeClass.AllSizeClasses
         * )
         * ```
         */
        @Suppress("ListIterator", "PrimitiveInCollection")
        val AllSizeClasses = AllSizeClassList.toSet()

        private fun WindowWidthSizeClass.breakpoint(): Int = when (this) {
            Expanded -> EXPANDED_PX
            Medium -> MEDIUM_PX
            Compact -> COMPACT_PX
        }

        /**
         * Calculates the best matched [WindowWidthSizeClass] for a given [width] in Pixels and a
         * given [Density] from [supportedSizeClasses].
         */
        internal fun fromWidth(
            width: Int,
            supportedSizeClasses: Set<WindowWidthSizeClass>
        ): WindowWidthSizeClass {
            require(width >= 0) { "Width must not be negative" }
            require(supportedSizeClasses.isNotEmpty()) { "Must support at least one size class" }
            var smallestSupportedSizeClass: WindowWidthSizeClass = Compact
            AllSizeClassList.fastForEach {
                if (it in supportedSizeClasses) {
                    if (width >= it.breakpoint()) {
                        return it
                    }
                    smallestSupportedSizeClass = it
                }
            }

            // If none of the size classes matches, return the largest one.
            return smallestSupportedSizeClass
        }
    }
}

/**
 * Height-based window size class.
 *
 * A window size class represents a breakpoint that can be used to build responsive layouts. Each
 * window size class breakpoint represents a majority case for typical device scenarios so your
 * layouts will work well on most devices and configurations.
 *
 * For more details see <a
 * href="https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#window_size_classes"
 * class="external" target="_blank">Window size classes documentation</a>.
 */
@Immutable
sealed class WindowHeightSizeClass : Comparable<WindowHeightSizeClass> {
    /** Represents the majority of phones in landscap. */
    data object Compact : WindowHeightSizeClass()

    /** Represents the majority of tablets in landscape and majority of phones in portrait. */
    data object Medium : WindowHeightSizeClass()

    /** Represents the majority of tablets in portrait. */
    data object Expanded : WindowHeightSizeClass()

    override operator fun compareTo(other: WindowHeightSizeClass) =
        breakpoint().compareTo(other.breakpoint())

    override fun toString(): String = "WindowHeightSizeClass." + when (this) {
        Compact -> "Compact"
        Medium -> "Medium"
        Expanded -> "Expanded"
    }

    companion object {
        var COMPACT_PX = 0
        var MEDIUM_PX = 480
        var EXPANDED_PX = 900

        /**
         * The default set of size classes that includes [Compact], [Medium], and [Expanded] size
         * classes. Should never expand to ensure behavioral consistency.
         */
        @Suppress("PrimitiveInCollection")
        val DefaultSizeClasses = setOf(Compact, Medium, Expanded)

        @Suppress("PrimitiveInCollection")
        private val AllSizeClassList = listOf(Expanded, Medium, Compact)

        /**
         * The set of all size classes. It's supposed to be expanded whenever a new size class is
         * defined. By default [WindowSizeClass.calculateFromSize] will only return size classes in
         * [DefaultSizeClasses] in order to avoid behavioral changes when new size classes are
         * added. You can opt in to support all available size classes by doing:
         * ```
         * WindowSizeClass.calculateFromSize(
         *     size = size,
         *     density = density,
         *     supportedWidthSizeClasses = WindowWidthSizeClass.AllSizeClasses,
         *     supportedHeightSizeClasses = WindowHeightSizeClass.AllSizeClasses
         * )
         * ```
         */
        @Suppress("ListIterator", "PrimitiveInCollection")
        val AllSizeClasses = AllSizeClassList.toSet()

        private fun WindowHeightSizeClass.breakpoint(): Int = when (this) {
            Expanded -> EXPANDED_PX
            Medium -> MEDIUM_PX
            Compact -> COMPACT_PX
        }

        /**
         * Calculates the best matched [WindowHeightSizeClass] for a given [height] in Pixels and a
         * given [Density] from [supportedSizeClasses].
         */
        internal fun fromHeight(
            height: Int,
            supportedSizeClasses: Set<WindowHeightSizeClass>
        ): WindowHeightSizeClass {
            require(height >= 0) { "Width must not be negative" }
            require(supportedSizeClasses.isNotEmpty()) { "Must support at least one size class" }
            var smallestSupportedSizeClass: WindowHeightSizeClass = Expanded
            AllSizeClassList.fastForEach {
                if (it in supportedSizeClasses) {
                    if (height >= it.breakpoint()) {
                        return it
                    }
                    smallestSupportedSizeClass = it
                }
            }

            // If none of the size classes matches, return the largest one.
            return smallestSupportedSizeClass
        }
    }
}
