package dev.kilua.compose.web.events

import androidx.compose.runtime.*
import dev.kilua.html.helpers.TagEvents
import web.dom.HTMLElement
import web.dom.events.Event
import web.window

@Composable
fun <E : HTMLElement> TagEvents<E>.onCombineClick(
    onClick: () -> Unit,
    onDoubleClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
) {
    var singleClickTimer by remember { mutableStateOf<Int?>(null) }
    var longClickTimer by remember { mutableStateOf<Int?>(null) }
    var clickCount by remember { mutableStateOf(0) }
    var isLongClickTriggered by remember { mutableStateOf(false) }

    // Long Click with Mouse and Touch
    val startEvent: (Event) -> Unit = remember {
        {
            isLongClickTriggered = false

            longClickTimer = window.setTimeout({
                isLongClickTriggered = true
                onLongClick?.invoke()
                clickCount = 0
                null
            }, LONG_CLICK_DURATION)
        }
    }

    val endEvent: (Event) -> Unit = remember {
        {
            longClickTimer?.let { window.clearTimeout(it) }
            longClickTimer = null
        }
    }

    // Mouse Events
    onMouseDown(startEvent)
    onMouseUp(endEvent)
    onMouseLeave(endEvent)

    // Touch Events
    onTouchStart(startEvent)
    onTouchEnd(endEvent)
    onTouchCancel(endEvent)

    // Click and Double Click
    onClick {
        if (isLongClickTriggered) {
            return@onClick
        }

        clickCount++
        if (clickCount == 1) {
            singleClickTimer = window.setTimeout({
                if (clickCount == 1) {
                    onClick()
                }
                clickCount = 0
                null
            }, DOUBLE_CLICK_DELAY)
        } else if (clickCount == 2) {
            singleClickTimer?.let { window.clearTimeout(it) }
            clickCount = 0
            onDoubleClick?.invoke()
        }
    }
}

private const val LONG_CLICK_DURATION = 500 // ms
private const val DOUBLE_CLICK_DELAY = 300 // ms
