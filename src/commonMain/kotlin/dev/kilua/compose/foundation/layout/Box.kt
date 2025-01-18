package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import dev.kilua.compose.style.toClassName
import dev.kilua.compose.ui.Alignment
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.attrsModifier
import dev.kilua.compose.ui.modifiers.classNames
import dev.kilua.core.IComponent
import dev.kilua.html.IDiv
import dev.kilua.html.div
import dev.kilua.utils.rem

@LayoutScopeMarker
@Immutable // TODO: Remove annotation after upstream fix
interface BoxScope {
    fun Modifier.align(alignment: Alignment) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

internal object BoxScopeInstance : BoxScope

/**
 * Add classes that tell the browser to display this element as a column.
 *
 * This method is public as there may occasionally be cases where users could benefit from using this, but in general
 * you shouldn't reach for this unless you know what you're doing.
 *
 * NOTE: This modifier sets attribute properties and can therefore not be used within CssStyles.
 */
fun Modifier.boxClasses(contentAlignment: Alignment = Alignment.TopStart): Modifier =
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

@Composable
fun IComponent.Box(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Div(modifier = modifier.boxClasses(contentAlignment)) {
        onClick { onClick() }
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
                selfAlignment?.let { "${it.toClassName()}-self" } % className,
        id = id,
        content = content,
    )
}
