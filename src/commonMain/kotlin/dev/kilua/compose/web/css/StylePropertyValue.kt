package dev.kilua.compose.web.css

import dev.kilua.utils.cast

external interface StylePropertyValue

external interface StylePropertyNumber : StylePropertyValue
external interface StylePropertyString : StylePropertyValue

inline fun StylePropertyValue(value: String): StylePropertyString = value.cast()
inline fun StylePropertyValue(value: Number): StylePropertyNumber = value.cast()

external interface CSSStyleValue : StylePropertyValue {
    override fun toString(): String
}

inline fun CSSStyleValue(value: String): CSSStyleValue = StylePropertyValue(value).cast()
