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
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.pokedex_chg.ui.components.splashScreen.AnimatedText
import com.example.pokedex_chg.ui.components.splashScreen.LogoImage
import com.example.pokedex_chg.ui.viewModels.PokemonDetailViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController, viewModel: PokemonDetailViewModel) {
    val pokemons = viewModel.pokemons.observeAsState()

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
    }

    LaunchedEffect(key1 = pokemons.value) {
        delay(3000)
        if (pokemons.value != null) {
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
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogoImage(
            painter = painterResource(id = R.drawable.logo),
            scale = scale,
            opacity = opacity
        )
        AnimatedText(
            text = "Pokedex",
            textColor = textColor,
            fontFamily = customFontFamily,
            scale = scale,
            opacity = opacity
        )
    }
}
