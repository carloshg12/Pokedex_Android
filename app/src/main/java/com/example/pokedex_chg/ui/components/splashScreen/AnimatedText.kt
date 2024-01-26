package com.example.pokedex_chg.ui.components.splashScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.animation.core.Animatable
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.alpha

@Composable
fun AnimatedText(
    text: String,
    textColor: Animatable<Color, *>,
    fontFamily: FontFamily,
    scale: Animatable<Float, *>,
    opacity: Animatable<Float, *>
) {
    Text(
        text = text,
        fontSize = 60.sp,
        fontFamily = fontFamily,
        color = textColor.value,
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(4f, 4f),
                blurRadius = 8f
            )
        ),
        modifier = Modifier
            .scale(scale.value)
            .alpha(opacity.value)
    )
}
