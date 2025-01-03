package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import dev.kilua.compose.style.alignmentToStyle
import dev.kilua.compose.ui.Alignment
import dev.kilua.core.IComponent
import dev.kilua.html.div

@LayoutScopeMarker
@Immutable // TODO: Remove annotation after upstream fix
interface BoxScope {
//    fun Modifier.align(alignment: Alignment) = attrsModifier {
//        classes("${alignment.toClassName()}-self")
//    }
}

internal object BoxScopeInstance : BoxScope

object BoxDefaults {
    val ContentAlignment: Alignment = Alignment.TopStart
}

@Composable
fun IComponent.box(
//    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    className: String? = null,
    id: String? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    div(className = className, id = id) {
        alignmentToStyle(contentAlignment)
        BoxScopeInstance.content()
    }
}
