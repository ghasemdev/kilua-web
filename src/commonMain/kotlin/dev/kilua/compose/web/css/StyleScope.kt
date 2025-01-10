package dev.kilua.compose.web.css

import dev.kilua.compose.web.attributes.HtmlAttrMarker
import dev.kilua.compose.web.internal.runtime.ComposeWebInternalApi
import dev.kilua.utils.cast
import kotlin.properties.ReadOnlyProperty

/**
 * StyleScope serves for two main purposes. Passed as a builder context (in [AttrsScope]), it
 * makes it possible to:
 * 1. Add inlined css properties to the element (@see [property])
 * 2. Set values to CSS variables (@see [variable])
 */
@HtmlAttrMarker
interface StyleScope {
    /**
     * Adds arbitrary CSS property to the inline style of the element
     * @param propertyName - the name of css property as [per spec](https://developer.mozilla.org/en-US/docs/Web/CSS/Reference)
     * @param value - the value, it can be either String or specialized type like [CSSNumeric] or [CSSColorValue]
     *
     * Most frequent CSS property values can be set via specialized methods, like [width], [display] etc.
     *
     * Example:
     * ```
     * Div({
     *  style {
     *      property("some-exotic-css-property", "I am a string value")
     *      property("some-exotic-css-property-width", 5.px)
     *  }
     * })
     * ```
     */
    fun property(propertyName: String, value: StylePropertyValue)
    fun variable(variableName: String, value: StylePropertyValue)

    fun property(propertyName: String, value: String) = property(propertyName, StylePropertyValue(value))
    fun property(propertyName: String, value: Number) = property(propertyName, StylePropertyValue(value))
    fun variable(variableName: String, value: String) = variable(variableName, StylePropertyValue(value))
    fun variable(variableName: String, value: Number) = variable(variableName, StylePropertyValue(value))

    operator fun <TValue : StylePropertyValue> CSSStyleVariable<TValue>.invoke(value: TValue) {
        variable(name, value.toString())
    }

    operator fun CSSStyleVariable<StylePropertyString>.invoke(value: String) {
        variable(name, value)
    }

    operator fun CSSStyleVariable<StylePropertyNumber>.invoke(value: Number) {
        variable(name, value)
    }
}

internal inline fun variableValue(variableName: String, fallback: StylePropertyValue? = null) =
    "var(--$variableName${fallback?.let { ", $it" } ?: ""})"

external interface CSSVariableValueAs<out T : StylePropertyValue>

inline fun <TValue> CSSVariableValue(value: StylePropertyValue) =
    value.cast<TValue>()

inline fun <TValue> CSSVariableValue(value: String) =
    CSSVariableValue<TValue>(StylePropertyValue(value))

interface CSSVariable {
    val name: String
}

class CSSStyleVariable<out TValue : StylePropertyValue>(override val name: String) : CSSVariable

fun <TValue : StylePropertyValue> CSSStyleVariable<TValue>.value(fallback: TValue? = null) =
    CSSVariableValue<TValue>(
        variableValue(
            name,
            fallback
        )
    )

fun <TValue> CSSStyleVariable<TValue>.value(fallback: TValue? = null)
        where TValue : CSSVariableValueAs<TValue>,
              TValue : StylePropertyValue =
    CSSVariableValue<TValue>(
        variableValue(
            name,
            fallback
        )
    )

/**
 * Introduces CSS variable that can be later referred anywhere in [StyleSheet]
 *
 * Example:
 * ```
 * object AppCSSVariables {
 *  val width by variable<CSSUnitValue>()
 *  val stringHeight by variable<StylePropertyString>()
 *  val order by variable<StylePropertyNumber>()
 * }
 *
 * object AppStylesheet : StyleSheet() {
 *    val classWithProperties by style {
 *     AppCSSVariables.width(100.px)
 *     property("width", AppCSSVariables.width.value())
 * }
 *```
 *
 */
fun <TValue : StylePropertyValue> variable() =
    ReadOnlyProperty<Any?, CSSStyleVariable<TValue>> { _, property ->
        CSSStyleVariable(property.name)
    }

interface StyleHolder {
    @ComposeWebInternalApi
    val properties: StylePropertyList

    @ComposeWebInternalApi
    val variables: StylePropertyList
}

@Deprecated(
    message = "Renamed to StyleScopeBuilder",
    replaceWith = ReplaceWith("StyleScopeBuilder", "org.jetbrains.compose.web.css.StyleScopeBuilder")
)
typealias StyleBuilderImpl = StyleScopeBuilder

@Suppress("EqualsOrHashCode")
@OptIn(ComposeWebInternalApi::class)
open class StyleScopeBuilder : StyleScope, StyleHolder {
    override val properties: MutableStylePropertyList = mutableListOf()
    override val variables: MutableStylePropertyList = mutableListOf()

    override fun property(propertyName: String, value: StylePropertyValue) {
        properties.add(StylePropertyDeclaration(propertyName, value))
    }

    override fun variable(variableName: String, value: StylePropertyValue) {
        variables.add(StylePropertyDeclaration(variableName, value))
    }

    // StylePropertyValue is js native object without equals
    override fun equals(other: Any?): Boolean {
        return if (other is StyleHolder) {
            properties.nativeEquals(other.properties) &&
                    variables.nativeEquals(other.variables)
        } else false
    }

    @ComposeWebInternalApi
    internal fun copyFrom(sb: StyleHolder) {
        properties.addAll(sb.properties)
        variables.addAll(sb.variables)
    }
}

data class StylePropertyDeclaration(
    val name: String,
    val value: StylePropertyValue
) {
    constructor(name: String, value: String) : this(name, value.cast<StylePropertyValue>())
    constructor(name: String, value: Number) : this(name, value.cast<StylePropertyValue>())
}
typealias StylePropertyList = List<StylePropertyDeclaration>
typealias MutableStylePropertyList = MutableList<StylePropertyDeclaration>

internal fun StylePropertyList.nativeEquals(properties: StylePropertyList): Boolean {
    if (this.size != properties.size) return false

    var index = 0
    return all { prop ->
        val otherProp = properties[index++]
        prop.name == otherProp.name &&
                prop.value.toString() == otherProp.value.toString()
    }
}
