package com.example.pokedex_chg.data.repositories

import com.example.pokedex_chg.data.models.ReducedPokemonData

interface PokemonRepository {
    suspend fun getAllPokemons(): List<ReducedPokemonData>
}

