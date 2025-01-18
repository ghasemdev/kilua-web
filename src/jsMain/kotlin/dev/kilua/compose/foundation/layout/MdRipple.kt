package dev.kilua.compose.foundation.layout

import web.JsAny
import web.dom.HTMLElement

@JsModule("@material/web/ripple/ripple.js")
@JsNonModule
actual external object MdRipple : JsAny {
    actual fun attach(control: HTMLElement)
}
