package com.example.pokedex_chg.data.sources.local

import android.app.Application
import com.example.pokedex_chg.data.dto.PokemonDTO
import com.example.pokedex_chg.domains.models.PokedexResponse
import com.example.pokedex_chg.domains.models.Pokemon
import com.example.pokedex_chg.domains.models.ReducedPokemonData
import com.example.pokedex_chg.domains.repositories.PokemonRepository
import com.example.pokedex_chg.mappers.mapPokemonEntryToReducedData
import com.example.pokedex_chg.mappers.mapToPokemon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.lang.reflect.Type
import javax.inject.Inject

class PokemonGson @Inject constructor(val application: Application): PokemonRepository {

    val context = application.applicationContext

    override fun getPokemonByArchive(name: String): Pokemon {
        val json = InputStreamReader(context.assets.open("$name.json"))
        val gson = Gson()
        val gsonPokemon = gson.fromJson(json, PokemonDTO::class.java)

        return mapToPokemon(gsonPokemon)
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPokemons(): List<ReducedPokemonData> {
        val json = InputStreamReader(context.assets.open("pokedex.json"))
        val gson = Gson()

        val responseType: Type = object : TypeToken<PokedexResponse>() {}.type
        val response = gson.fromJson<PokedexResponse>(json, responseType)

        return response.pokemon_entries.map { entry ->
            mapPokemonEntryToReducedData(entry)
        }
    }

}