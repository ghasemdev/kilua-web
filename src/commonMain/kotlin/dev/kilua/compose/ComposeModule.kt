package dev.kilua.compose

import dev.kilua.CssRegister
import dev.kilua.JsModule
import dev.kilua.ModuleInitializer
import dev.kilua.useModule
import web.JsAny

@JsModule("./k-compose.css")
internal external object ComposeCss : JsAny

@JsModule("beercss/dist/cdn/beer.min.css")
internal external object BeerCss : JsAny

@JsModule("beercss/dist/cdn/beer.min.js")
internal external object BeerJS : JsAny

/**
 * Initializer for Kilua compose module.
 */
object ComposeModule : ModuleInitializer {
    override fun initialize() {
        useModule(ComposeCss)
        CssRegister.register("./k-compose.css")

        useModule(BeerJS)
        useModule(BeerCss)
        CssRegister.register("beercss/dist/cdn/beer.min.css")
    }
}
