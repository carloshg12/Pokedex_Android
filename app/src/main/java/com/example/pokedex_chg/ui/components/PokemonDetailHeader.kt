package com.example.pokedex_chg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokedex_chg.ui.screens.NetworkImage

@Composable
fun PokemonDetailHeader(pokemonPhotoUrl: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 35.dp, 35.dp))
    ) {
        NetworkImage(
            url = pokemonPhotoUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
                .background(backgroundColor)
        )
    }
}
