package com.example.pokedex_chg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex_chg.View.Pokedex_View
import com.example.pokedex_chg.ViewModel.Pokedex_ViewModel
import com.example.pokedex_chg.ui.theme.Pokedex_CHGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pokedex_CHGTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "PokedexView"
                    ) {
                        composable("PokedexView") {

                            Pokedex_View().PokemonDetailScreen(pokedexViewModel = Pokedex_ViewModel(application))
                        }
                    }
                }
            }
        }
    }
}

/*

       Numero -> 460
       Foto -> 729
       Nombre -> 716
       Habilidades -> 953 - 954
       Peso ->959
       Altura -> 169
       HP -> 902
       ATCK -> 910
       DFN -> 918
       SPD -> 942
       EXP -> 20

 */
