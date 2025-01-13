package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import dev.kilua.compose.style.toClassName
import dev.kilua.compose.style.toClassNames
import dev.kilua.compose.ui.Alignment
import dev.kilua.compose.ui.Arrangement
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.SpacedAligned
import dev.kilua.compose.ui.attrsModifier
import dev.kilua.compose.ui.modifiers.classNames
import dev.kilua.compose.ui.modifiers.setVariable
import dev.kilua.compose.ui.thenIfNotNull
import dev.kilua.compose.web.css.StyleVariable
import dev.kilua.core.IComponent
import dev.kilua.html.IDiv
import dev.kilua.html.div
import dev.kilua.utils.rem

@LayoutScopeMarker
@Immutable // TODO(#554): Remove annotation after upstream fix
interface ColumnScope : FlexScope {
    fun Modifier.align(alignment: Alignment.Horizontal) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

internal object ColumnScopeInstance : ColumnScope

/**
 * Add classes that tell the browser to display this element as a column.
 *
 * This method is public as there may occasionally be cases where users could benefit from using this, but in general
 * you shouldn't reach for this unless you know what you're doing.
 *
 * NOTE: This modifier sets attribute properties and can therefore not be used within CssStyles.
 */
fun Modifier.columnClasses(
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) = classNames("kilua-col", *verticalArrangement.toClassNames(), horizontalAlignment.toClassName())

@Composable
fun IComponent.Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Div(
        modifier = modifier
            .columnClasses(verticalArrangement, horizontalAlignment)
            .thenIfNotNull(verticalArrangement as? SpacedAligned) {
                Modifier.setVariable(
                    variable = StyleVariable.StringValue(name = "kilua-col-gap"),
                    value = it.spacing.value
                )
            },
    ) {
        ColumnScopeInstance.content()
    }
}

@Composable
fun IComponent.column(
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    className: String? = null,
    id: String? = null,
    content: @Composable IDiv.() -> Unit = {},
) {
    div(
        className = "kilua-col" %
                horizontalAlignment.toClassName() %
                verticalArrangement.toClassNames().joinToString(" ") %
                (verticalArrangement as? SpacedAligned)?.let { "--kilua-col-gap: ${it.spacing.value};" } %
                className,
        id = id,
        content = content,
    )
}
