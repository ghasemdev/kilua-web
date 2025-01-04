package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import dev.kilua.compose.style.alignmentToStyle
import dev.kilua.compose.style.selfAlignmentToStyle
import dev.kilua.compose.ui.Alignment
import dev.kilua.core.IComponent
import dev.kilua.html.Color
import dev.kilua.html.ITag
import dev.kilua.html.div
import dev.kilua.html.helpers.TagStyleFun.Companion.background
import dev.kilua.html.px
import web.dom.HTMLElement

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
//    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    className: String? = null,
    id: String? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    div(className = className, id = id) {
        maxWidth(200.px)
        height(200.px)
        background(color = Color.Aqua)
//        position(Position.Relative)

        alignmentToStyle(contentAlignment)
        BoxScopeInstance.content()
    }
}
