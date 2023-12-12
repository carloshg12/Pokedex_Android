package com.example.pokedex_chg.View

import androidx.compose.ui.graphics.Color
import com.example.pokedex_chg.ui.theme.bug
import com.example.pokedex_chg.ui.theme.dark
import com.example.pokedex_chg.ui.theme.dragon
import com.example.pokedex_chg.ui.theme.electric
import com.example.pokedex_chg.ui.theme.fairy
import com.example.pokedex_chg.ui.theme.fighting
import com.example.pokedex_chg.ui.theme.fire
import com.example.pokedex_chg.ui.theme.flying
import com.example.pokedex_chg.ui.theme.ghost
import com.example.pokedex_chg.ui.theme.grass
import com.example.pokedex_chg.ui.theme.ground
import com.example.pokedex_chg.ui.theme.ice
import com.example.pokedex_chg.ui.theme.normal
import com.example.pokedex_chg.ui.theme.poison
import com.example.pokedex_chg.ui.theme.psychic
import com.example.pokedex_chg.ui.theme.rock
import com.example.pokedex_chg.ui.theme.shadow
import com.example.pokedex_chg.ui.theme.steel
import com.example.pokedex_chg.ui.theme.unknown
import com.example.pokedex_chg.ui.theme.water

enum class Enum_Colors (val color: Color){

    NORMAL(normal),
    FIGHTING(fighting),
    FLYING(flying),
    POISON(poison),
    GROUND(ground),
    ROCK(rock),
    BUG(bug),
    GHOST(ghost),
    STEEL(steel),
    FIRE(fire),
    WATER(water),
    GRASS(grass),
    ELECTRIC(electric),
    PSYCHIC(psychic),
    ICE(ice),
    DRAGON(dragon),
    DARK(dark),
    FAIRY(fairy),
    UNKNOWN(unknown),
    LOADING(Color.Gray),
    SHADOW(shadow);


}

