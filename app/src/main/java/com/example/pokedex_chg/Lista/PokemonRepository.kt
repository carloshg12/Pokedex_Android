package com.example.pokedex_chg.Lista

import java.util.Locale

interface PokemonRepository {
    suspend fun getAllPokemons(): List<Pokemon_Reduced>
}

class PokemonRepositoryImpl : PokemonRepository {
    private val apiService = RetrofitInstance.api

    override suspend fun getAllPokemons(): List<Pokemon_Reduced> {
        val response = apiService.getAllPokemons()
        return response.pokemon_entries.map { entry ->
            val pokemonId = entry.entry_number.toString()
            val pokemonName = entry.pokemon_species.name.capitalize(Locale.getDefault())
            val pokemonPhotoUrl = getPokemonPhotoUrl(entry.entry_number)

            Pokemon_Reduced(pokemonId, pokemonName, pokemonPhotoUrl)
        }
    }

    private fun getPokemonPhotoUrl(id: Int): String {
        // Asume una URL de imagen específica o modifícala según la API
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}
