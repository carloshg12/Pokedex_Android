package com.example.pokedex_chg.data.sources.local

import com.example.pokedex_chg.domains.models.Pokemon
import com.example.pokedex_chg.data.dto.PokemonDTO
import com.example.pokedex_chg.domains.models.ReducedPokemonData
import com.example.pokedex_chg.domains.repositories.PokemonRepository
import com.example.pokedex_chg.mappers.mapToPokemon
import com.google.gson.Gson
import org.json.simple.JSONObject


class PokemonGson : PokemonRepository {
    override fun getPokemonByArchive(jsonObject: JSONObject): Pokemon {
        val jsonString = jsonObject.toString()
        val gson = Gson()
        val gsonPokemon = gson.fromJson(jsonString, PokemonDTO::class.java)

        return mapToPokemon(gsonPokemon)
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPokemons(): List<ReducedPokemonData> {
        TODO("Not yet implemented")
    }
}