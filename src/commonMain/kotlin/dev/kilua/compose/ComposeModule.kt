package dev.kilua.compose

import dev.kilua.CssRegister
import dev.kilua.JsModule
import dev.kilua.ModuleInitializer
import dev.kilua.useModule
import web.JsAny

@JsModule("./k-compose.css")
internal external object ComposeCss : JsAny

@JsModule("./k-ripple-script.js")
internal external object RippleScript : JsAny

/**
 * Initializer for Kilua compose module.
 */
object ComposeModule : ModuleInitializer {
    override fun initialize() {
        useModule(ComposeCss)
        CssRegister.register("./k-compose.css")

        useModule(RippleScript)
    }
}
