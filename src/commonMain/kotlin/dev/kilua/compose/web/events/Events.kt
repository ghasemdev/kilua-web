package dev.kilua.compose.web.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import dev.kilua.compose.ui.geometry.Size
import dev.kilua.html.helpers.TagEvents
import web.document
import web.dom.HTMLElement
import web.dom.TouchEvent
import web.dom.events.Event
import web.dom.events.MouseEvent
import web.dom.pointerevents.PointerEvent
import web.window

@Composable
fun <E : HTMLElement> TagEvents<E>.onTouchStart(listener: (TouchEvent) -> Unit) =
    onEvent(TOUCH_START, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onTouchEnd(listener: (TouchEvent) -> Unit) =
    onEvent(TOUCH_END, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onTouchCancel(listener: (TouchEvent) -> Unit) =
    onEvent(TOUCH_CANCEL, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onMouseDown(listener: (MouseEvent) -> Unit) =
    onEvent(MOUSE_DOWN, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onMouseUp(listener: (MouseEvent) -> Unit) =
    onEvent(MOUSE_UP, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onMouseLeave(listener: (MouseEvent) -> Unit) =
    onEvent(MOUSE_LEAVE, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onMouseOver(listener: (MouseEvent) -> Unit) =
    onEvent(MOUSE_OVER, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onMouseOut(listener: (MouseEvent) -> Unit) =
    onEvent(MOUSE_OUT, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onMouseMove(listener: (MouseEvent) -> Unit) =
    onEvent(MOUSE_MOVE, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onPointerDown(listener: (PointerEvent) -> Unit) =
    onEvent(POINTER_DOWN, listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onPointerUp(listener: (PointerEvent) -> Unit) =
    onEvent(POINTER_UP, listener)

@Composable
fun onGlobalWindowSize(callback: (size: Size) -> Unit) {
    DisposableEffect(Unit) {
        val listener = { _: Event ->
            callback(
                Size(
                    width = window.innerWidth.toFloat(),
                    height = window.innerHeight.toFloat()
                )
            )
        }
        window.addEventListener(RESIZE, listener)
        onDispose { window.removeEventListener(RESIZE, listener) }
    }
}

@Composable
fun onGlobalPointerUp(callback: () -> Unit) {
    DisposableEffect(Unit) {
        val listener = { _: Event -> callback() }
        document.addEventListener(POINTER_UP, listener)
        onDispose { document.removeEventListener(POINTER_UP, listener) }
    }
}

@Composable
fun onGlobalPointerMove(callback: (e: PointerEvent) -> Unit) {
    DisposableEffect(Unit) {
        val listener = { e: Event -> callback(e as PointerEvent) }
        document.addEventListener(POINTER_MOVE, listener)
        onDispose { document.removeEventListener(POINTER_MOVE, listener) }
    }
}

private const val TOUCH_START = "touchstart"
private const val TOUCH_END = "touchend"
private const val TOUCH_CANCEL = "touchcancel"
private const val MOUSE_DOWN = "mousedown"
private const val MOUSE_UP = "mouseup"
private const val MOUSE_LEAVE = "mouseleave"
private const val MOUSE_OVER = "mouseover"
private const val MOUSE_OUT = "mouseout"
private const val MOUSE_MOVE = "mousemove"
private const val POINTER_DOWN = "pointerdown"
private const val POINTER_MOVE = "pointermove"
private const val POINTER_UP = "pointerup"
private const val RESIZE = "resize"
