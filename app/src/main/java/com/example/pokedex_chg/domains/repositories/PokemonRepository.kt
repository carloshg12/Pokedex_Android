package com.example.pokedex_chg.domains.repositories

import com.example.pokedex_chg.domains.models.Pokemon
import com.example.pokedex_chg.domains.models.ReducedPokemonData
import org.json.simple.JSONObject

interface PokemonRepository {
    fun getPokemonByArchive(jsonObject: JSONObject): Pokemon
    suspend fun getPokemonByName(name: String): Pokemon
    suspend fun getPokemonById(id: Int) : Pokemon
    suspend fun getAllPokemons(): List<ReducedPokemonData>


}
