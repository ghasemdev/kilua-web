package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import dev.kilua.compose.style.toClassName
import dev.kilua.compose.style.toClassNames
import dev.kilua.compose.ui.*
import dev.kilua.compose.ui.modifiers.classNames
import dev.kilua.compose.ui.modifiers.setVariable
import dev.kilua.compose.web.css.StyleVariable
import dev.kilua.core.IComponent
import dev.kilua.html.IDiv
import dev.kilua.html.div
import dev.kilua.utils.rem

@LayoutScopeMarker
@Immutable // TODO(#554): Remove annotation after upstream fix
interface RowScope : FlexScope {
    fun Modifier.align(alignment: Alignment.Vertical) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

internal object RowScopeInstance : RowScope

/**
 * Add classes that tell the browser to display this element as a row.
 *
 * This method is public as there may occasionally be cases where users could benefit from using this, but in general
 * you shouldn't reach for this unless you know what you're doing.
 *
 * NOTE: This modifier sets attribute properties and can therefore not be used within CssStyles.
 */
fun Modifier.rowClasses(
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
) = classNames("kilua-row", *horizontalArrangement.toClassNames(), verticalAlignment.toClassName())

@Composable
fun IComponent.Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
) {
    Div(
        modifier = modifier
            .rowClasses(horizontalArrangement, verticalAlignment)
            .thenIfNotNull(horizontalArrangement as? SpacedAligned) {
                Modifier.setVariable(
                    variable = StyleVariable.StringValue(name = "kilua-row-gap"),
                    value = it.spacing.value
                )
            }
    ) {
        RowScopeInstance.content()
    }
}

@Composable
fun IComponent.row(
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    className: String? = null,
    id: String? = null,
    content: @Composable IDiv.() -> Unit = {},
) {
    div(
        className = "kilua-row" %
                verticalAlignment.toClassName() %
                horizontalArrangement.toClassNames().joinToString(" ") %
                (horizontalArrangement as? SpacedAligned)?.let { "--kilua-row-gap: ${it.spacing.value};" } %
                className,
        id = id,
        content = content,
    )
}
