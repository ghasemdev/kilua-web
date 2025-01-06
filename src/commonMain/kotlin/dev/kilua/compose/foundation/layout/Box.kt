package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import dev.kilua.compose.style.toClassName
import dev.kilua.compose.style.toClassNameSelf
import dev.kilua.compose.ui.Alignment
import dev.kilua.core.IComponent
import dev.kilua.html.IDiv
import dev.kilua.html.div
import dev.kilua.utils.rem

@LayoutScopeMarker
@Immutable // TODO: Remove annotation after upstream fix
interface BoxScope {
    //    fun Modifier.align(alignment: Alignment) = attrsModifier {
//        classes("${alignment.toClassName()}-self")
//    }

//    @Composable
//    fun <T : HTMLElement> ITag<T>.align(alignment: Alignment) {
//        selfAlignmentToStyle(alignment)
//    }
}

internal object BoxScopeInstance : BoxScope

object BoxDefaults {
    val ContentAlignment: Alignment = Alignment.TopStart
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
