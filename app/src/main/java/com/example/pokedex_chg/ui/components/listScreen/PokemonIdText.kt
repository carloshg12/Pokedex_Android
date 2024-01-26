package com.example.pokedex_chg.ui.components.listScreen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PokemonIdText(pokemonId: String) {
    Text(
        text = "ID: $pokemonId",
        style = MaterialTheme.typography.labelSmall
    )
}
