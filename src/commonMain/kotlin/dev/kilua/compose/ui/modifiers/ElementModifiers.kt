package dev.kilua.compose.ui.modifiers

import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.attrsModifier

//fun Modifier.appearance(appearance: Appearance) = styleModifier {
//    appearance(appearance)
//}

fun Modifier.classNames(vararg classes: String) = attrsModifier {
    classes(*classes)
}

//fun Modifier.content(content: Content.Restricted) = styleModifier {
//    content(content)
//}

//fun Modifier.content(vararg contents: Content.Unrestricted) = styleModifier {
//    content(*contents)
//}

//fun Modifier.content(altText: String, vararg contents: Content.Unrestricted) = styleModifier {
//    content(altText, *contents)
//}

//fun Modifier.content(value: String) = styleModifier {
//    content(value)
//}

fun Modifier.hidden() = attrsModifier {
    hidden()
}

fun Modifier.id(value: String) = attrsModifier {
    id(value)
}

fun Modifier.title(value: String) = attrsModifier {
    title(value)
}
