package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.*
import dev.kilua.compose.style.toClassName
import dev.kilua.compose.ui.Alignment
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.attrsModifier
import dev.kilua.compose.ui.modifiers.classNames
import dev.kilua.core.IComponent
import dev.kilua.html.IDiv
import dev.kilua.html.div
import dev.kilua.utils.rem
import web.dom.TouchEvent
import web.dom.events.Event
import web.dom.events.MouseEvent
import web.window

@LayoutScopeMarker
@Immutable // TODO: Remove annotation after upstream fix
interface BoxScope {
    fun Modifier.align(alignment: Alignment) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

internal object BoxScopeInstance : BoxScope

/**
 * Add classes that tell the browser to display this element as a column.
 *
 * This method is public as there may occasionally be cases where users could benefit from using this, but in general
 * you shouldn't reach for this unless you know what you're doing.
 *
 * NOTE: This modifier sets attribute properties and can therefore not be used within CssStyles.
 */
fun Modifier.boxClasses(contentAlignment: Alignment = Alignment.TopStart): Modifier =
    classNames("kilua-box", contentAlignment.toClassName())

@Composable
fun IComponent.Box(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Div(modifier = modifier.boxClasses(contentAlignment)) {
        BoxScopeInstance.content()
    }
}

private const val LONG_CLICK_DURATION = 500 // ms
private const val DOUBLE_CLICK_DELAY = 300 // ms

@Composable
fun IComponent.Box(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    onClick: () -> Unit,
    onDoubleClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit = {}
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


    Div(
        modifier = modifier
            .classNames("ripple")
            .boxClasses(contentAlignment)
    ) {
        // Mouse Events
        onEvent<MouseEvent>("mousedown", startEvent)
        onEvent<MouseEvent>("mouseup", endEvent)
        onEvent<MouseEvent>("mouseleave", endEvent)

        // Touch Events
        onEvent<TouchEvent>("touchstart", startEvent)
        onEvent<TouchEvent>("touchend", endEvent)
        onEvent<TouchEvent>("touchcancel", endEvent)

        // Click and Double Click
        onEvent<MouseEvent>("click") {
            if (isLongClickTriggered) {
                return@onEvent
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

        BoxScopeInstance.content()
    }
}

@Composable
fun IComponent.box(
    contentAlignment: Alignment = Alignment.TopStart,
    selfAlignment: Alignment? = null,
    className: String? = null,
    id: String? = null,
    content: @Composable IDiv.() -> Unit = {},
) {
    div(
        className = "kilua-box" % contentAlignment.toClassName() %
                selfAlignment?.let { "${it.toClassName()}-self" } % className,
        id = id,
        content = content,
    )
}
