package com.example.pokedex_chg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PokemonAbilitiesRow(abilities: List<String>, abilityColorProvider: (String) -> Color) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(abilities) { _, ability ->
            val color = abilityColorProvider(ability)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color, shape = CircleShape)
                    .width(150.dp)
            ) {
                Text(
                    text = ability,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}
