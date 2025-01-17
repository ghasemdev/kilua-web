package dev.kilua.compose.adaptive

import androidx.compose.runtime.*

enum class Breakpoint {
    Mobile,
    SmallTablet,
    Tablet,
    Laptop,
    Desktop;

    infix fun isGreaterThan(breakpoint: Breakpoint) = this.ordinal > breakpoint.ordinal
    infix fun isSmallerThan(breakpoint: Breakpoint) = this.ordinal < breakpoint.ordinal
    fun isBetween(smallBreakpoint: Breakpoint, largeBreakpoint: Breakpoint): Boolean {
        return (smallBreakpoint.ordinal..largeBreakpoint.ordinal).contains(this.ordinal)
    }
}

@Composable
fun rememberBreakpoint(): State<Breakpoint> {
    val isMobile by rememberMediaQueryAsState("(max-width: 480px)")
    val isSmallTablet by rememberMediaQueryAsState("(min-width: 481px) and (max-width: 768px)")
    val isTablet by rememberMediaQueryAsState("(min-width: 769px) and (max-width: 991px)")
    val isLaptop by rememberMediaQueryAsState("(min-width: 992px) and (max-width: 1199px)")

    return remember {
        derivedStateOf {
            when {
                isMobile -> Breakpoint.Mobile
                isSmallTablet -> Breakpoint.SmallTablet
                isTablet -> Breakpoint.Tablet
                isLaptop -> Breakpoint.Laptop
                else -> Breakpoint.Desktop
            }
        }
    }
}
