package com.example.pokedex_chg.ui.components.listScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PokemonNameText(pokemonName: String, modifier: Modifier = Modifier) {
    Text(
        text = pokemonName,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}
