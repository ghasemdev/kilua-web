package dev.kilua.compose.foundation.layout

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import dev.kilua.html.Color

@Composable
fun animateColorInfinite(
    initialValue: Color,
    targetValue: Color,
): State<Color> {
    val infiniteTransition = rememberInfiniteTransition()
    val blinking by infiniteTransition.animateColor(
        initialValue = initialValue.toComposeColor(),
        targetValue = targetValue.toComposeColor(),
        animationSpec = infiniteRepeatable(animation = tween())
    )

    val kiluaColor = remember {
        derivedStateOf {
            blinking.toKiluaColor()
        }
    }

    return kiluaColor
}

fun androidx.compose.ui.graphics.Color.toKiluaColor(): Color = Color(toHexString())

fun androidx.compose.ui.graphics.Color.toHexString(): String {
    val alpha = (this.alpha * 255).toInt()
    val red = (this.red * 255).toInt()
    val green = (this.green * 255).toInt()
    val blue = (this.blue * 255).toInt()
    return "#${red.toString(16).padStart(2, '0').uppercase()}" +
            green.toString(16).padStart(2, '0').uppercase() +
            blue.toString(16).padStart(2, '0').uppercase() +
            alpha.toString(16).padStart(2, '0').uppercase()
}

fun Color.toComposeColor(): androidx.compose.ui.graphics.Color = value.toComposeColor()

fun String.toComposeColor(): androidx.compose.ui.graphics.Color {
    val sanitizedHex = this.removePrefix("#")

    return when (sanitizedHex.length) {
        3 -> Color(
            red = sanitizedHex[0].toString().toInt(16).toFloat() / 15,
            green = sanitizedHex[1].toString().toInt(16).toFloat() / 15,
            blue = sanitizedHex[2].toString().toInt(16).toFloat() / 15
        )

        4 -> Color(
            alpha = sanitizedHex[0].toString().toInt(16).toFloat() / 15,
            red = sanitizedHex[1].toString().toInt(16).toFloat() / 15,
            green = sanitizedHex[2].toString().toInt(16).toFloat() / 15,
            blue = sanitizedHex[3].toString().toInt(16).toFloat() / 15
        )

        6 -> Color(
            red = sanitizedHex.substring(0, 2).toInt(16).toFloat() / 255,
            green = sanitizedHex.substring(2, 4).toInt(16).toFloat() / 255,
            blue = sanitizedHex.substring(4, 6).toInt(16).toFloat() / 255
        )

        8 -> Color(
            alpha = sanitizedHex.substring(0, 2).toInt(16).toFloat() / 255,
            red = sanitizedHex.substring(2, 4).toInt(16).toFloat() / 255,
            green = sanitizedHex.substring(4, 6).toInt(16).toFloat() / 255,
            blue = sanitizedHex.substring(6, 8).toInt(16).toFloat() / 255
        )

        else -> throw IllegalArgumentException("Invalid hex color format")
    }
}
