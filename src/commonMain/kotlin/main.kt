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
import dev.kilua.compose.root
import dev.kilua.html.*
import dev.kilua.html.helpers.TagStyleFun.Companion.background
import dev.kilua.panel.gridPanel
import dev.kilua.startApplication

class App : Application() {
    override fun start() {
        root("root") {
//            box(contentAlignment = Alignment.Center) {
//                /*div {
//                    selfAlignmentToStyle(Alignment.BottomStart)
//                    width(50.px)
//                    height(50.px)
//                    background(color = Color.Red)
//                }*/
//                div {
//
//                    width(50.px)
//                    height(50.px)
//                    background(color = Color.Blue)
//                }
//            }
            gridPanel(
                gridTemplateColumns = "minmax(0px, 1fr)",
                gridTemplateRows = "minmax(0px, 1fr)",
                alignItems = AlignItems.End,
                justifyItems = JustifyItems.End,
            ) {
                maxWidth(200.px)
                height(200.px)
                background(color = Color.Blue)
                gridArea("1 / 1")

                div {
                    maxWidth(50.px)
                    height(50.px)
                    background(color = Color.Red)
                }

                div {
                    maxWidth(50.px)
                    height(50.px)
                    background(color = Color.Yellow)
                }

                div {
                    maxWidth(50.px)
                    height(50.px)
                    background(color = Color.Green)
                }
            }
        }
    }

    override fun dispose(): String? {
        return null
    }
}

fun main() {
    startApplication(::App, webpackHot())
}

expect fun webpackHot(): Hot?
