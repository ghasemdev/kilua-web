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
import dev.kilua.html.AlignItems
import dev.kilua.html.Color
import dev.kilua.html.Display
import dev.kilua.html.FlexDirection
import dev.kilua.html.JustifyContent
import dev.kilua.html.div
import dev.kilua.html.helpers.TagStyleFun.Companion.background
import dev.kilua.html.perc
import dev.kilua.html.pt
import dev.kilua.html.vh
import dev.kilua.startApplication

class App : Application() {
    override fun start() {
        root("root") {
            div {
                display(Display.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Center)
                justifyContent(JustifyContent.Center)
                width(100.perc)
                height(100.vh)
                background(Color.Lightblue)

                div {
                    pt("First")
                    pt("Second")
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
