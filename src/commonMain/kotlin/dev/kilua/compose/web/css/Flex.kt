package dev.kilua.compose.web.css

import dev.kilua.html.*

//region jetbrains

fun StyleScope.flexDirection(flexDirection: FlexDirection) {
    property("flex-direction", flexDirection.value)
}

fun StyleScope.flexWrap(flexWrap: FlexWrap) {
    property("flex-wrap", flexWrap.value)
}

fun StyleScope.flexFlow(flexDirection: FlexDirection, flexWrap: FlexWrap) {
    property("flex-flow", "${flexDirection.value} ${flexWrap.value}")
}

fun StyleScope.justifyContent(justifyContent: JustifyContent) {
    property("justify-content", justifyContent.value)
}

//fun StyleScope.alignSelf(alignSelf: AlignSelf) {
//    property("align-self", alignSelf.value)
//}

fun StyleScope.alignItems(alignItems: AlignItems) {
    property("align-items", alignItems.value)
}

fun StyleScope.alignContent(alignContent: AlignContent) {
    property("align-content", alignContent.value)
}

fun StyleScope.order(value: Int) {
    property("order", value)
}

fun StyleScope.flexGrow(value: Number) {
    property("flex-grow", value)
}

fun StyleScope.flexShrink(value: Number) {
    property("flex-shrink", value)
}

// https://developer.mozilla.org/en-US/docs/Web/CSS/flex-basis
fun StyleScope.flexBasis(value: String) {
    property("flex-basis", value)
}

fun StyleScope.flexBasis(value: CssSize) {
    property("flex-basis", value.value)
}

// https://developer.mozilla.org/en-US/docs/Web/CSS/flex
fun StyleScope.flex(value: String) {
    property("flex", value)
}

fun StyleScope.flex(value: Int) {
    property("flex", value)
}

fun StyleScope.flex(value: CssSize) {
    property("flex", value.value)
}

fun StyleScope.flex(flexGrow: Int, flexBasis: CssSize) {
    property("flex", "$flexGrow $flexBasis")
}

fun StyleScope.flex(flexGrow: Int, flexShrink: Int) {
    property("flex", "$flexGrow $flexShrink")
}

fun StyleScope.flex(flexGrow: Int, flexShrink: Int, flexBasis: CssSize) {
    property("flex", "$flexGrow $flexShrink $flexBasis")
}

//endregion

//region kobweb

// See: https://developer.mozilla.org/en-US/docs/Web/CSS/flex-basis
//class FlexBasis private constructor(private val value: String) : StylePropertyValue {
//    override fun toString() = value
//
//    companion object {
//        // Width
//        val Auto get() = FlexBasis("auto")
//
//        // Intrinsic sizing
//        val MaxContent get() = FlexBasis("max-content")
//        val MinContent get() = FlexBasis("min-content")
//        val FitContent get() = FlexBasis("fit-content")
//
//        // Content sizing
//        val Content get() = FlexBasis("content")
//
//        // Global
//        val Inherit get() = FlexBasis("inherit")
//        val Initial get() = FlexBasis("initial")
//        val Revert get() = FlexBasis("revert")
//        val Unset get() = FlexBasis("unset")
//    }
//}
//
//fun StyleScope.flexBasis(flexBasis: FlexBasis) {
//    property("flex-basis", flexBasis)
//}

// See: https://developer.mozilla.org/en-US/docs/Web/CSS/flex-direction
//val FlexDirection.Inherit get() = FlexDirection("inherit")
//val FlexDirection.Initial get() = FlexDirection("initial")
//val FlexDirection.Revert get() = FlexDirection("revert")
//val FlexDirection.Unset get() = FlexDirection("unset")

//endregion
