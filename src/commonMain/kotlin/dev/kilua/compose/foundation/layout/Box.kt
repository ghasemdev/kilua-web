package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
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
import dev.kilua.html.*
import dev.kilua.html.helpers.TagStyleFun.Companion.background
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
    width: CssSize = 200.px,
    height: CssSize = 200.px,
    background: Color = Color.Gray,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val attrsScopeBuilder = remember(modifier) {
        AttrsScopeBuilder<HTMLDivElement>().apply {
            modifier
                .boxClasses(contentAlignment)
                .toAttrs<AttrsScope<HTMLDivElement>>()
                .invoke(this)
        }
    }

    div(className = attrsScopeBuilder.classes.joinToString(separator = " ")) {
        attrsScopeBuilder.attributesMap.forEach { (name, value) -> attribute(name = name, value = value) }

        width(width)
        height(height)
        background(color = background)

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
                selfAlignment?.toClassNameSelf() % className,
        id = id,
        content = content,
    )
}
