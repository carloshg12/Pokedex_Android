package com.example.pokedex_chg.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pokedex_chg.ui.viewModels.Pokedex_ViewModel
import com.example.pokedex_chg.ui.theme.ATKColor
import com.example.pokedex_chg.ui.theme.DFNColor
import com.example.pokedex_chg.ui.theme.EXPColor
import com.example.pokedex_chg.ui.theme.HPColor
import com.example.pokedex_chg.ui.theme.SPDColor
import com.example.pokedex_chg.ui.theme.lightDark
import com.example.pokedex_chg.ui.theme.lightGray
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val PokemonName = "charizard"

class Pokedex_View {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PokemonDetailScreen(
        pokedexViewModel: Pokedex_ViewModel = viewModel(),
        navController: NavController
    ) {

        /*
            * Si queremos leer mediante GSON un archivo json descomentamos estas lineas
            * y cambiamos en el constructor el parser
         */
        /*val context: Context = LocalContext.current

        val jsonString = context.assets.open("$PokemonName.json")
        pokedexViewModel.loadPokemonData(jsonString)*/

        val pokemon by pokedexViewModel.pokemon.observeAsState()
        val number = pokemon?.number
        val color = pokedexViewModel.primaryColor

        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(color)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(number, color, navController) }) {

            Column(
                modifier = Modifier
                    .background(lightDark)
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(0.dp, 0.dp, 35.dp, 35.dp))
                ) {
                    NetworkImage(
                        url = pokemon?.photo ?: "",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp)
                            .background(color)
                    )
                }

                Spacer(modifier = Modifier.size(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                        text = "${pokemon?.name?.capitalize() ?: "Loading..."}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,

                        )
                }

                Spacer(modifier = Modifier.size(15.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val abilities = pokemon?.abilities ?: listOf("Loading...")

                    LazyRow(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        itemsIndexed(abilities) { index, ability ->

                            val color = pokedexViewModel.getAbilityColor(ability)

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

                Spacer(modifier = Modifier.size(15.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            text = "${pokemon?.weight ?: "Loading..."} KG",
                        )

                        Text(
                            fontSize = 15.sp,
                            color = lightGray,
                            text = "Weight",
                        )


                    }

                    Spacer(modifier = Modifier.width(80.dp))


                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            text = "${pokemon?.height ?: "Loading..."} M",
                        )


                        Text(
                            fontSize = 15.sp,
                            color = lightGray,
                            text = "Height",
                        )

                    }

                }


                Spacer(modifier = Modifier.size(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.White,
                        text = "Base Stats",
                    )
                }

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

    @SuppressLint("RememberReturnType")
    @Composable
    fun NetworkImage(
        url: String,
        contentDescription: String?,
        modifier: Modifier = Modifier
    ) {

        val painter: AsyncImagePainter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                }).build()
        )

        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(number: String?, color: Color, navController: NavController) {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(color),
            title = {
                Text(
                    text = "Pokedex", color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás",
                        tint = Color.White
                    )
                }
            },
            actions = {
                Text(
                    text = "#${number ?: "Loading..."}", color = Color.White
                )
            }
        )
    }


    @Composable
    fun StatisticBar(value: Int, maxValue: Int, color: Color) {
        val percentage = (value / maxValue.toFloat()).coerceIn(0f, 1f)

        Column(
            modifier = Modifier
                .padding(start = 20.dp, top = 15.dp, end = 20.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(10.dp))
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(percentage)
                        .height(20.dp)
                        .background(color, shape = RoundedCornerShape(10.dp))
                )

                Text(
                    text = "$value/$maxValue",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 4.dp)
                )
            }
        }
    }
}