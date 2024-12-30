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
import dev.kilua.Application
import dev.kilua.compose.root
import dev.kilua.html.ITable
import dev.kilua.html.div
import dev.kilua.html.img
import dev.kilua.html.perc
import dev.kilua.html.style.style
import dev.kilua.html.table
import dev.kilua.html.td
import dev.kilua.html.th
import dev.kilua.html.tr
import dev.kilua.startApplication

class App : Application() {
    override fun start() {
        root("root") {
            div {
                img(src = "https://avatars.githubusercontent.com/u/65798992?s=96&v=4") {
                    style {
                        borderRadius(50.perc)
                        onClick {  }
                    }
                }
            }
            table {
                tableRow()
                tableRow()
                tableRow()
            }
        }
    }

    @Composable
    private fun ITable.tableRow() {
        tr {
            th {
                +"a"
            }
            td {
                +"b"
            }
            td {
                +"c"
            }
        }
    }
}

fun main() {
    startApplication(::App)
}
