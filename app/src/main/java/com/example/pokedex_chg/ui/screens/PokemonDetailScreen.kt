package com.example.pokedex_chg.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedex_chg.ui.components.*
import com.example.pokedex_chg.ui.components.detailScreen.BaseStatsHeader
import com.example.pokedex_chg.ui.components.detailScreen.NetworkImage
import com.example.pokedex_chg.ui.components.detailScreen.PokemonAbilitiesRow
import com.example.pokedex_chg.ui.components.detailScreen.PokemonInfoRow
import com.example.pokedex_chg.ui.components.detailScreen.PokemonNameRow
import com.example.pokedex_chg.ui.components.detailScreen.StatisticBar
import com.example.pokedex_chg.ui.components.detailScreen.TopBar
import com.example.pokedex_chg.ui.viewModels.PokemonListViewModel
import com.example.pokedex_chg.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    pokedexViewModel: PokemonListViewModel,
    navController: NavController
) {
    val pokemon by pokedexViewModel.pokemon.observeAsState()
    val number = pokemon?.number
    val color = pokedexViewModel.primaryColor

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(number, color, navController) }
    ) {
        Column(
            modifier = Modifier
                .background(lightDark)
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            NetworkImage(
                url = pokemon?.photo ?: "",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
                    .background(color)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 35.dp, 35.dp))
            )

            Spacer(modifier = Modifier.size(25.dp))

            PokemonNameRow(pokemonName = pokemon?.name)

            Spacer(modifier = Modifier.size(15.dp))

            PokemonAbilitiesRow(abilities = pokemon?.abilities ?: listOf("Loading..."), pokedexViewModel::getAbilityColor)

            Spacer(modifier = Modifier.size(15.dp))

            PokemonInfoRow(
                label1 = "Weight",
                value1 = "${pokemon?.weight ?: "Loading..."} KG",
                label2 = "Height",
                value2 = "${pokemon?.height ?: "Loading..."} M"
            )

            Spacer(modifier = Modifier.size(5.dp))

            BaseStatsHeader()

            Spacer(modifier = Modifier.size(5.dp))

            val stats = listOf(
                Triple("HP  ", pokemon?.hp ?: 0, 300 to HPColor),
                Triple("ATK", pokemon?.attack ?: 0, 300 to ATKColor),
                Triple("DFN", pokemon?.defense ?: 0, 300 to DFNColor),
                Triple("SPD", pokemon?.speed ?: 0, 300 to SPDColor),
                Triple("EXP", pokemon?.experience ?: 0, 1000 to EXPColor)
            )

            stats.forEach { (statName, value, maxValueColor) ->
                val (maxValue, color) = maxValueColor
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp, top = 15.dp),
                        color = color,
                        text = "$statName "
                    )
                    StatisticBar(value = value, maxValue = maxValue, color = color)
                }
            }
        }
    }
}
