package com.example.pokedex_chg.ui.components.detailScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex_chg.ui.theme.lightGray

@Composable
fun PokemonInfoRow(label1: String, value1: String, label2: String, value2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(color = Color.White, fontSize = 25.sp, text = value1)
            Text(fontSize = 15.sp, color = lightGray, text = label1)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(color = Color.White, fontSize = 25.sp, text = value2)
            Text(fontSize = 15.sp, color = lightGray, text = label2)
        }
    }
}
