package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import dev.kilua.compose.ComponentNode
import dev.kilua.compose.style.toClassName
import dev.kilua.compose.style.toClassNameSelf
import dev.kilua.compose.ui.Alignment
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.attrsModifier
import dev.kilua.compose.ui.modifiers.classNames
import dev.kilua.compose.ui.toAttrs
import dev.kilua.compose.web.attributes.AttrsScope
import dev.kilua.compose.web.attributes.AttrsScopeBuilder
import dev.kilua.core.IComponent
import dev.kilua.html.Div
import dev.kilua.html.IDiv
import dev.kilua.html.div
import dev.kilua.utils.rem
import web.dom.HTMLDivElement

@LayoutScopeMarker
@Immutable // TODO: Remove annotation after upstream fix
interface BoxScope {
    fun Modifier.align(alignment: Alignment) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

internal object BoxScopeInstance : BoxScope

object BoxDefaults {
    val ContentAlignment: Alignment = Alignment.TopStart
}

/**
 * Add classes that tell the browser to display this element as a column.
 *
 * This method is public as there may occasionally be cases where users could benefit from using this, but in general
 * you shouldn't reach for this unless you know what you're doing.
 *
 * NOTE: This modifier sets attribute properties and can therefore not be used within CssStyles.
 */
fun Modifier.boxClasses(contentAlignment: Alignment = BoxDefaults.ContentAlignment): Modifier =
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
                selfAlignment?.toClassNameSelf() % className,
        id = id,
        content = content,
    )
}
