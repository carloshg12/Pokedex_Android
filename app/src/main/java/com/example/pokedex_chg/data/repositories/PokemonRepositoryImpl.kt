package com.example.pokedex_chg.data.repositories

import com.example.pokedex_chg.data.models.ReducedPokemonData
import com.example.pokedex_chg.data.sources.remote.RetrofitInstance
import java.util.Locale

class PokemonRepositoryImpl : PokemonRepository {
    private val apiService = RetrofitInstance.api

    override suspend fun getAllPokemons(): List<ReducedPokemonData> {
        val response = apiService.getAllPokemons()
        return response.pokemon_entries.map { entry ->
            val pokemonId = entry.entry_number.toString()
            val pokemonName = entry.pokemon_species.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            val pokemonPhotoUrl = getPokemonPhotoUrl(entry.entry_number)

            ReducedPokemonData(pokemonId, pokemonName, pokemonPhotoUrl)
        }
    }

    private fun getPokemonPhotoUrl(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}
