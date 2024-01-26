package com.example.pokedex_chg.ui.components.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.animation.core.Animatable
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale

@Composable
fun LogoImage(painter: Painter, scale: Animatable<Float, *>, opacity: Animatable<Float, *>) {
    Image(
        painter = painter,
        contentDescription = "Logo",
        modifier = Modifier
            .scale(scale.value)
            .alpha(opacity.value)
    )
}
