package dev.kilua.compose.ui.modifiers

import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.styleModifier
import dev.kilua.compose.web.css.StylePropertyValue
import dev.kilua.compose.web.css.StyleVariable
import dev.kilua.compose.web.css.setVariable

/**
 * Set the value of a variable.
 *
 * If the value passed in is null, this method is a no-op. This approach is supported since it is common to have
 * methods that take a nullable parameter which should only override some value if non-null.
 *
 * @see StyleVariable
 */
fun <T : StylePropertyValue> Modifier.setVariable(
    variable: StyleVariable.PropertyValue<T>,
    value: T?
) = if (value != null) styleModifier {
    setVariable(variable, value)
} else this

/**
 * Set the value of a variable.
 *
 * If the value passed in is null, this method is a no-op. This approach is supported since it is common to have
 * methods that take a nullable parameter which should only override some value if non-null.
 *
 * @see StyleVariable
 */
fun <T : Number> Modifier.setVariable(
    variable: StyleVariable.NumberValue<T>,
    value: T?
) = if (value != null) styleModifier {
    setVariable(variable, value)
} else this

/**
 * Set the value of a variable.
 *
 * If the value passed in is null, this method is a no-op. This approach is supported since it is common to have
 * methods that take a nullable parameter which should only override some value if non-null.
 *
 * @see StyleVariable
 */
fun Modifier.setVariable(
    variable: StyleVariable.StringValue,
    value: String?
) = if (value != null) styleModifier {
    setVariable(variable, value)
} else this
