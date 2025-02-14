/*
 * Copyright (c) 2024 Robert Jaros
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import dev.kilua.Application
import dev.kilua.Hot
import dev.kilua.compose.ComposeModule
import dev.kilua.compose.adaptive.*
import dev.kilua.compose.foundation.layout.*
import dev.kilua.compose.root
import dev.kilua.compose.ui.Alignment
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.modifiers.background
import dev.kilua.compose.ui.modifiers.classNames
import dev.kilua.compose.ui.modifiers.height
import dev.kilua.compose.ui.modifiers.width
import dev.kilua.core.IComponent
import dev.kilua.html.*
import dev.kilua.startApplication

var currentPath = mutableStateOf("/login")
fun navigateTo(path: String) {
    currentPath.value = path
}

class App : Application() {
    override fun start() {
        root("root") {
            box(contentAlignment = Alignment.Center) {
                width(100.vw)
                height(100.vh)

                when (currentPath.value) {
                    "/login" -> LoginPage("page active")
                    "/home" -> HomePage("page active")
                }
            }
        }
    }

    @Composable
    fun IComponent.HomePage(className: String) {
        box(contentAlignment = Alignment.Center, className = className) {
            width(100.vw)
            height(100.vh)

            column {
                divt(
                    "Lorem ipsum odor amet, consectetuer adipiscing elit." +
                            " Consectetur porttitor pretium praesent tincidunt " +
                            "himenaeos donec consequat. Donec elementum facilisis " +
                            "vivamus facilisis eu ornare placerat parturient. Purus" +
                            " phasellus sodales nulla quis ut quis sit. At eleifend " +
                            "aptent venenatis lacus montes varius class diam velit. Eu " +
                            "scelerisque semper justo class suscipit massa. Conubia dui o" +
                            "rnare dapibus nisl taciti. Maecenas nibh sapien tellus platea," +
                            " congue conubia condimentum finibus."
                )
                button {
                    onClick { navigateTo("/login") }
                    +"go to login"
                }
            }
        }
    }

    @Composable
    fun IComponent.LoginPage(className: String) {
        box(contentAlignment = Alignment.Center, className = className) {
            width(100.vw)
            height(100.vh)

            column {
                divt(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo " +
                            "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat " +
                            "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                )
                button {
                    onClick { navigateTo("/home") }
                    +"go to dashboard"
                }
            }
        }
    }

    @Composable
    private fun IComponent.Clickable() {
        Column {
            Box(
                modifier = Modifier
                    .width(500.px)
                    .height(100.px)
                    .background(Color("#19273B"))
                    .classNames("ripple", "hover"),
            )
        }
    }

    @Composable
    private fun IComponent.ResponsiveSolutions() {
        val windowAdaptiveInfo = currentWindowAdaptiveInfo().windowSizeClass
        val breakpoint by rememberBreakpoint()
        val tailwindcssBreakpoint by rememberTailwindcssBreakpoint()
        val orientation by rememberOrientation()

        Row {
            Box(
                modifier = Modifier
                    .width(
                        when (windowAdaptiveInfo.widthSizeClass) {
                            WindowWidthSizeClass.Compact -> 50.px
                            WindowWidthSizeClass.Medium -> 100.px
                            WindowWidthSizeClass.Expanded -> 200.px
                        }
                    )
                    .height(
                        when (windowAdaptiveInfo.widthSizeClass) {
                            WindowWidthSizeClass.Compact -> 50.px
                            WindowWidthSizeClass.Medium -> 100.px
                            WindowWidthSizeClass.Expanded -> 200.px
                        }
                    )
                    .background(Color.Red)
            )

            Box(
                modifier = Modifier
                    .width(
                        when (breakpoint) {
                            Breakpoint.Mobile -> 50.px
                            Breakpoint.SmallTablet -> 100.px
                            Breakpoint.Tablet -> 150.px
                            Breakpoint.Laptop -> 200.px
                            Breakpoint.Desktop -> 300.px
                        }
                    )
                    .height(
                        when (breakpoint) {
                            Breakpoint.Mobile -> 50.px
                            Breakpoint.SmallTablet -> 100.px
                            Breakpoint.Tablet -> 150.px
                            Breakpoint.Laptop -> 200.px
                            Breakpoint.Desktop -> 300.px
                        }
                    )
                    .background(Color.Green)
            )

            Box(
                modifier = Modifier
                    .width(
                        when (tailwindcssBreakpoint) {
                            TailwindcssBreakpoint.DEFAULT -> 50.px
                            TailwindcssBreakpoint.SM -> 100.px
                            TailwindcssBreakpoint.MD -> 150.px
                            TailwindcssBreakpoint.LG -> 200.px
                            TailwindcssBreakpoint.XL -> 250.px
                            TailwindcssBreakpoint.XL2 -> 300.px
                        }
                    )
                    .height(
                        when (tailwindcssBreakpoint) {
                            TailwindcssBreakpoint.DEFAULT -> 50.px
                            TailwindcssBreakpoint.SM -> 100.px
                            TailwindcssBreakpoint.MD -> 150.px
                            TailwindcssBreakpoint.LG -> 200.px
                            TailwindcssBreakpoint.XL -> 250.px
                            TailwindcssBreakpoint.XL2 -> 300.px
                        }
                    )
                    .background(Color.Blue)
            )

            divt(orientation.toString())
        }
    }

    @Composable
    private fun IComponent.ComposeLayout() {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.px)
        ) {
            Column(
                modifier = Modifier
                    .width(100.px)
                    .height(30.px)
                    .background(color = Color.Cyan),
            ) {}
            Column(
                modifier = Modifier
                    .width(100.px)
                    .height(30.px)
                    .background(color = Color.Yellowgreen),
            ) {}
            Column(
                modifier = Modifier
                    .width(100.px)
                    .height(30.px)
                    .background(color = Color.Linen),
            ) {}
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.px),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(200.px)
                .height(50.px)
                .background(color = Color.Red),
        ) {
            Row(
                modifier = Modifier
                    .width(50.px)
                    .height(30.px)
                    .background(color = Color.Cyan),
            ) {}
            Row(
                modifier = Modifier
                    .width(50.px)
                    .height(30.px)
                    .background(color = Color.Yellowgreen),
            ) {}
            Row(
                modifier = Modifier
                    .width(50.px)
                    .height(30.px)
                    .background(color = Color.Linen),
            ) {}
        }
        Box(
            modifier = Modifier
                .width(200.px)
                .height(200.px)
                .background(color = Color.Gray),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .width(50.px)
                    .height(50.px)
                    .background(color = Color.Red)
                    .align(Alignment.BottomEnd),
            )
            Box(
                modifier = Modifier
                    .width(50.px)
                    .height(50.px)
                    .background(color = Color.Blue)
                    .align(Alignment.TopStart),
            )
            Box(
                modifier = Modifier
                    .width(50.px)
                    .height(50.px)
                    .background(color = Color.Yellow),
            )
        }
    }

    override fun dispose(): String? = null
}

fun main() {
    startApplication(::App, webpackHot(), ComposeModule)
}

expect fun webpackHot(): Hot?
