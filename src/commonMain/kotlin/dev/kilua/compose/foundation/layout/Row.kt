package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import dev.kilua.compose.style.toClassName
import dev.kilua.compose.style.toClassNames
import dev.kilua.compose.ui.Alignment
import dev.kilua.compose.ui.Arrangement
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.attrsModifier
import dev.kilua.compose.ui.modifiers.classNames
import dev.kilua.core.IComponent

@LayoutScopeMarker
@Immutable // TODO(#554): Remove annotation after upstream fix
interface RowScope : FlexScope {
    fun Modifier.align(alignment: Alignment.Vertical) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

internal object RowScopeInstance : RowScope

object RowDefaults {
    val HorizontalArrangement: Arrangement.Horizontal = Arrangement.Start
    val VerticalAlignment: Alignment.Vertical = Alignment.Top
}

/**
 * Add classes that tell the browser to display this element as a row.
 *
 * This method is public as there may occasionally be cases where users could benefit from using this, but in general
 * you shouldn't reach for this unless you know what you're doing.
 *
 * NOTE: This modifier sets attribute properties and can therefore not be used within CssStyles.
 */
fun Modifier.rowClasses(
    horizontalArrangement: Arrangement.Horizontal = RowDefaults.HorizontalArrangement,
    verticalAlignment: Alignment.Vertical = RowDefaults.VerticalAlignment,
) = classNames("kilua-row", *horizontalArrangement.toClassNames(), verticalAlignment.toClassName())

@Composable
fun IComponent.Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = RowDefaults.HorizontalArrangement,
    verticalAlignment: Alignment.Vertical = RowDefaults.VerticalAlignment,
    content: @Composable RowScope.() -> Unit
) {
    Div(
        modifier = modifier
            .rowClasses(horizontalArrangement, verticalAlignment)
//            .thenIfNotNull(horizontalArrangement as? SpacedAligned) {
//                Modifier.setVariable(ArrangeSpacedByVar, it.spacing)
//            }
    ) {
        RowScopeInstance.content()
    }
}
