package dev.kilua.compose.adaptive

import androidx.compose.runtime.*
import dev.kilua.compose.adaptive.TailwindcssBreakpoint.Companion.MIN_WIDTH_2XL
import dev.kilua.compose.adaptive.TailwindcssBreakpoint.Companion.MIN_WIDTH_LG
import dev.kilua.compose.adaptive.TailwindcssBreakpoint.Companion.MIN_WIDTH_MD
import dev.kilua.compose.adaptive.TailwindcssBreakpoint.Companion.MIN_WIDTH_SM
import dev.kilua.compose.adaptive.TailwindcssBreakpoint.Companion.MIN_WIDTH_XL

enum class TailwindcssBreakpoint {
    DEFAULT,
    SM,
    MD,
    LG,
    XL,
    XL2;

    infix fun isGreaterThan(breakpoint: TailwindcssBreakpoint) = this.ordinal > breakpoint.ordinal
    infix fun isSmallerThan(breakpoint: TailwindcssBreakpoint) = this.ordinal < breakpoint.ordinal
    fun isBetween(smallBreakpoint: TailwindcssBreakpoint, largeBreakpoint: TailwindcssBreakpoint): Boolean {
        return (smallBreakpoint.ordinal..largeBreakpoint.ordinal).contains(this.ordinal)
    }

    companion object {
        var MIN_WIDTH_SM = 640
        var MIN_WIDTH_MD = 768
        var MIN_WIDTH_LG = 1024
        var MIN_WIDTH_XL = 1280
        var MIN_WIDTH_2XL = 1536
    }
}

@Composable
fun rememberTailwindcssBreakpoint(): State<TailwindcssBreakpoint> {
    val isSm by rememberMediaQueryAsState("(min-width: ${MIN_WIDTH_SM}px) and (max-width: ${MIN_WIDTH_MD - 1}px)")
    val isMd by rememberMediaQueryAsState("(min-width: ${MIN_WIDTH_MD}px) and (max-width: ${MIN_WIDTH_LG - 1}px)")
    val isLg by rememberMediaQueryAsState("(min-width: ${MIN_WIDTH_LG}px) and (max-width: ${MIN_WIDTH_XL - 1}px)")
    val isXl by rememberMediaQueryAsState("(min-width: ${MIN_WIDTH_XL}px) and (max-width: ${MIN_WIDTH_2XL - 1}px)")
    val is2Xl by rememberMediaQueryAsState("(min-width: ${MIN_WIDTH_2XL}px)")

    return remember {
        derivedStateOf {
            when {
                isSm -> TailwindcssBreakpoint.SM
                isMd -> TailwindcssBreakpoint.MD
                isLg -> TailwindcssBreakpoint.LG
                isXl -> TailwindcssBreakpoint.XL
                is2Xl -> TailwindcssBreakpoint.XL2
                else -> TailwindcssBreakpoint.DEFAULT
            }
        }
    }
}
