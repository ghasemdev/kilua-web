package dev.kilua.compose.adaptive

import androidx.compose.runtime.*
import dev.kilua.compose.ui.geometry.Size
import dev.kilua.compose.web.events.onGlobalWindowSize
import web.window

/**
 * Calculates and returns [WindowAdaptiveInfo] of the provided context. It's a convenient function
 * that uses the default [WindowSizeClass] constructor and the default [Posture] calculation
 * functions to retrieve [WindowSizeClass] and [Posture].
 *
 * @return [WindowAdaptiveInfo] of the provided context
 */
@Composable
fun currentWindowAdaptiveInfo(): WindowAdaptiveInfo {
    var size by remember { mutableStateOf(getCurrentWindowSize()) }
    val adaptiveInfo = remember(size) { WindowAdaptiveInfo(WindowSizeClass.calculateFromSize(size)) }
    onGlobalWindowSize { size = it }

    return adaptiveInfo
}

private fun getCurrentWindowSize(): Size = Size(
    width = window.innerWidth.toFloat(),
    height = window.innerHeight.toFloat()
)

/**
 * This class collects window info that affects adaptation decisions. An adaptive layout is supposed
 * to use the info from this class to decide how the layout is supposed to be adapted.
 *
 * @param windowSizeClass [WindowSizeClass] of the current window.
 * @param windowPosture [Posture] of the current window.
 * @constructor create an instance of [WindowAdaptiveInfo]
 */
@Immutable
class WindowAdaptiveInfo(val windowSizeClass: WindowSizeClass) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WindowAdaptiveInfo) return false
        if (windowSizeClass != other.windowSizeClass) return false
        return true
    }

    override fun hashCode(): Int = windowSizeClass.hashCode()

    override fun toString(): String {
        return "WindowAdaptiveInfo(windowSizeClass=$windowSizeClass)"
    }
}
