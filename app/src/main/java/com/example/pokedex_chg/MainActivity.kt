package com.example.pokedex_chg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.pokedex_chg.ui.viewModels.PokemonDetailViewModel
import com.example.pokedex_chg.data.models.ReducedPokemonData
import com.example.pokedex_chg.ui.screens.PokemonDetailScreen
import com.example.pokedex_chg.ui.viewModels.PokemonListViewModel
import com.example.pokedex_chg.ui.theme.Pokedex_CHGTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pokedex_CHGTheme {
                val navController = rememberNavController()

                val viewModel: PokemonDetailViewModel = viewModel()
                val viewModel2: PokemonListViewModel = viewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "Lista"
                    ) {

                        composable("Lista") {
                            PokemonListScreen(viewModel, navController)
                        }
                        composable("PokedexView/{pokemonId}") { backStackEntry ->
                            val pokemonId = backStackEntry.arguments?.getString("pokemonId")
                            PokemonDetailScreen(
                                pokedexViewModel = viewModel2,
                                navController = navController
                            ).apply {
                                viewModel2.getPokemonDetails(pokemonId?.toInt() ?: 0)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PokemonListScreen(viewModel: PokemonDetailViewModel, navController: NavController) {
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
                Text(
                    text = "ID: ${pokemon.id}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = pokemon.name,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                AsyncImage(
                    model = pokemon.photo,
                    contentDescription = "Imagen de ${pokemon.name}",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}