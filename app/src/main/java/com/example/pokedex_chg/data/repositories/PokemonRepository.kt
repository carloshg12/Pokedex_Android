package com.example.pokedex_chg.data.repositories

import com.example.pokedex_chg.data.models.ReducedPokemonData
import com.example.pokedex_chg.data.sources.remote.RetrofitInstance
import java.util.Locale

interface PokemonRepository {
    suspend fun getAllPokemons(): List<ReducedPokemonData>
}

class PokemonRepositoryImpl : PokemonRepository {
    private val apiService = RetrofitInstance.api

    override suspend fun getAllPokemons(): List<ReducedPokemonData> {
        val response = apiService.getAllPokemons()
        return response.pokemon_entries.map { entry ->
            val pokemonId = entry.entry_number.toString()
            val pokemonName = entry.pokemon_species.name.capitalize(Locale.getDefault())
            val pokemonPhotoUrl = getPokemonPhotoUrl(entry.entry_number)

            ReducedPokemonData(pokemonId, pokemonName, pokemonPhotoUrl)
        }
    }

    private fun getPokemonPhotoUrl(id: Int): String {
        // Asume una URL de imagen específica o modifícala según la API
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}
