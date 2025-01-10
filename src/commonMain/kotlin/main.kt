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

import dev.kilua.Application
import dev.kilua.Hot
import dev.kilua.compose.ComposeModule
import dev.kilua.compose.foundation.layout.Box
import dev.kilua.compose.root
import dev.kilua.compose.ui.Alignment
import dev.kilua.compose.ui.Modifier
import dev.kilua.compose.ui.modifiers.height
import dev.kilua.compose.ui.modifiers.width
import dev.kilua.html.Color
import dev.kilua.html.px
import dev.kilua.startApplication

class App : Application() {
    override fun start() {
        root("root") {
            Box(
                modifier = Modifier
                    .width(200.px)
                    .height(200.px),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .width(50.px)
                        .height(50.px)
                        .align(Alignment.BottomEnd),
                    background = Color.Red,
                )
                Box(
                    modifier = Modifier
                        .width(50.px)
                        .height(50.px)
                        .align(Alignment.TopStart),
                    background = Color.Blue,
                )
                Box(
                    modifier = Modifier
                        .width(50.px)
                        .height(50.px),
                    background = Color.Yellow,
                )
            }
        }
    }

    override fun dispose(): String? = null
}

fun main() {
    startApplication(::App, webpackHot(), ComposeModule)
}

expect fun webpackHot(): Hot?
