package com.example.pokedex_chg.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedex_chg.R
import com.example.pokedex_chg.ui.viewModels.PokemonListViewModel
import com.example.pokedex_chg.domains.models.ReducedPokemonData
import com.example.pokedex_chg.ui.components.listScreen.PokemonIdText
import com.example.pokedex_chg.ui.components.listScreen.PokemonImage
import com.example.pokedex_chg.ui.components.listScreen.PokemonNameText
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PokemonListScreen(viewModel: PokemonListViewModel, navController: NavController) {
    val color = MaterialTheme.colorScheme.primary
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color)

    val pokemons by viewModel.pokemons.observeAsState(initial = emptyList())

    LazyColumn {
        items(pokemons) { pokemon ->
            PokemonRow(pokemon) { pokemonId ->
                navController.navigate("PokedexView/$pokemonId")
            }
        }
    }
}

@Composable
fun PokemonRow(pokemon: ReducedPokemonData, onPokemonClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onPokemonClick(pokemon.id) }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                PokemonIdText(pokemonId = pokemon.id)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PokemonNameText(
                    pokemonName = pokemon.name,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                PokemonImage(
                    url = if (pokemon.photo == "no_foto") R.drawable.pokeball else pokemon.photo ,
                    contentDescription = "Imagen de ${pokemon.name}",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}
