//import androidx.compose.animation.animateColor
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.runtime.*
//import dev.kilua.html.Color
//import androidx.compose.ui.graphics.Color as ComposeColor
//
//@Composable
//fun animateColorInfinite(
//    initialValue: ComposeColor,
//    targetValue: ComposeColor,
//): State<Color> {
//    val infiniteTransition = rememberInfiniteTransition()
//    val blinking by infiniteTransition.animateColor(
//        initialValue = initialValue,
//        targetValue = targetValue,
//        animationSpec = infiniteRepeatable(animation = tween())
//    )
//
//    return remember {
//        derivedStateOf {
//            blinking.toKiluaColor()
//        }
//    }
//}
//
//fun ComposeColor.toKiluaColor(): Color = Color(toHexString())
//
//fun ComposeColor.toHexString(): String {
//    val alpha = (this.alpha * 255).toInt()
//    val red = (this.red * 255).toInt()
//    val green = (this.green * 255).toInt()
//    val blue = (this.blue * 255).toInt()
//    return "#${red.toString(16).padStart(2, '0').uppercase()}" +
//            green.toString(16).padStart(2, '0').uppercase() +
//            blue.toString(16).padStart(2, '0').uppercase() +
//            alpha.toString(16).padStart(2, '0').uppercase()
//}
//
//fun Color.toComposeColor(): ComposeColor = value.toComposeColor()
//
//fun String.toComposeColor(): ComposeColor {
//    val sanitizedHex = this.removePrefix("#")
//
//    return when (sanitizedHex.length) {
//        3 -> ComposeColor(
//            red = sanitizedHex[0].toString().toInt(16).toFloat() / 15,
//            green = sanitizedHex[1].toString().toInt(16).toFloat() / 15,
//            blue = sanitizedHex[2].toString().toInt(16).toFloat() / 15
//        )
//
//        4 -> ComposeColor(
//            alpha = sanitizedHex[0].toString().toInt(16).toFloat() / 15,
//            red = sanitizedHex[1].toString().toInt(16).toFloat() / 15,
//            green = sanitizedHex[2].toString().toInt(16).toFloat() / 15,
//            blue = sanitizedHex[3].toString().toInt(16).toFloat() / 15
//        )
//
//        6 -> ComposeColor(
//            red = sanitizedHex.substring(0, 2).toInt(16).toFloat() / 255,
//            green = sanitizedHex.substring(2, 4).toInt(16).toFloat() / 255,
//            blue = sanitizedHex.substring(4, 6).toInt(16).toFloat() / 255
//        )
//
//        8 -> ComposeColor(
//            alpha = sanitizedHex.substring(0, 2).toInt(16).toFloat() / 255,
//            red = sanitizedHex.substring(2, 4).toInt(16).toFloat() / 255,
//            green = sanitizedHex.substring(4, 6).toInt(16).toFloat() / 255,
//            blue = sanitizedHex.substring(6, 8).toInt(16).toFloat() / 255
//        )
//
//        else -> throw IllegalArgumentException("Invalid hex color format")
//    }
//}
