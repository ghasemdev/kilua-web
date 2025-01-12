package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.kilua.compose.ComponentNode
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.toAttrs
import dev.kilua.compose.web.attributes.AttrsScope
import dev.kilua.compose.web.attributes.AttrsScopeBuilder
import dev.kilua.core.IComponent
import dev.kilua.html.Div
import dev.kilua.html.IDiv
import web.dom.HTMLDivElement

/**
 * Creates a [Div] component.
 *
 * @param modifier modifier
 * @param content the content of the component
 */
@Composable
fun IComponent.Div(
    modifier: Modifier = Modifier,
    content: @Composable IDiv.() -> Unit = {}
) {
    val attrsScopeBuilder = remember(modifier) {
        AttrsScopeBuilder<HTMLDivElement>().apply {
            modifier
                .toAttrs<AttrsScope<HTMLDivElement>>()
                .invoke(this)
        }
    }
    val className = remember(attrsScopeBuilder) {
        attrsScopeBuilder.classes.joinToString(separator = " ")
    }
    val id = null

    val component = remember {
        Div(className = className, renderConfig = renderConfig).apply {
            attrsScopeBuilder.attributesMap.forEach { (name, value) ->
                setAttribute(name = name, value = value)
            }
            attrsScopeBuilder.styleScope.properties.forEach { (name, value) ->
                setStyle(name = name, value = value.toString())
            }
        }
    }

    ComponentNode(component, {
        set(className) { updateProperty(Div::className, it) }
        set(id) { updateProperty(Div::id, it) }
    }, content)
}
