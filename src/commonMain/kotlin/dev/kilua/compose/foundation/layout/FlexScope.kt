package dev.kilua.compose.foundation.layout

import androidx.compose.runtime.Immutable
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.modifiers.flexGrow

@LayoutScopeMarker
@Immutable // TODO(#554): Remove annotation after upstream fix
interface FlexScope {
    // Convenient remapping to "flexGrow" for users coming from the world of Android
    fun Modifier.weight(value: Number) = flexGrow(value)
}
