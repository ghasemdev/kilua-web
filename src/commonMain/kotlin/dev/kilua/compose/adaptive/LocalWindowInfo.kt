package dev.kilua.compose.adaptive

import dev.kilua.compose.ui.geometry.Size
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import web.window

object LocalWindowInfo {
    private val _containerSize = MutableStateFlow(getCurrentWindowSize())
    val containerSize: StateFlow<Size> = _containerSize

    init {
        // Add event listener to update size on window resize
        window.addEventListener("resize", {
            _containerSize.value = getCurrentWindowSize()
        })
    }

    private fun getCurrentWindowSize(): Size {
        return Size(
            width = window.innerWidth.toFloat(),
            height = window.innerHeight.toFloat()
        )
    }
}
