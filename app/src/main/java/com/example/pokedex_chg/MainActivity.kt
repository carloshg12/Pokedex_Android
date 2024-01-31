package com.example.pokedex_chg

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex_chg.ui.screens.PokemonDetailScreen
import com.example.pokedex_chg.ui.screens.PokemonListScreen
import com.example.pokedex_chg.ui.screens.SplashScreen
import com.example.pokedex_chg.ui.theme.Pokedex_CHGTheme
import com.example.pokedex_chg.ui.viewModels.PokemonDetailViewModel
import com.example.pokedex_chg.ui.viewModels.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pokedex_CHGTheme {
                val navController = rememberNavController()
                val viewModel: PokemonListViewModel = hiltViewModel()
                val viewModel2: PokemonDetailViewModel = hiltViewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "Splash"
                    ) {
                        composable("Lista") {
                            PokemonListScreen(viewModel, navController)
                        }
                        composable("Splash") {
                            SplashScreen(navController, viewModel)
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