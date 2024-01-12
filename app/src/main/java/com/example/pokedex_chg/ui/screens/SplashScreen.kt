package com.example.pokedex_chg.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedex_chg.R
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashScreen(navController: NavController, onSplashEnd: () -> Unit) {

    val scale = remember { Animatable(0f) }
    val opacity = remember { Animatable(1f) }
    val textColor = remember { Animatable(Color(0xFFFF0000)) }

    val customFontFamily = FontFamily(Font(R.font.pokemon))

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        )

        delay(1000)

        textColor.animateTo(
            targetValue = Color(0xFF0000FF),
            animationSpec = TweenSpec(durationMillis = 1500)
        )

        scale.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 300)
        )
        opacity.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 300)
        )

        navController.navigate("Lista") {
            popUpTo("Splash") { inclusive = true }
        }
        onSplashEnd()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .alpha(opacity.value)
        )
        Text(
            text = "Pokedex",
            fontSize = 60.sp,
            fontFamily = customFontFamily,
            color = textColor.value,
            style = androidx.compose.ui.text.TextStyle(
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
}
