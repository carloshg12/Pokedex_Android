package com.example.pokedex_chg.data.repositories

import com.example.pokedex_chg.data.sources.remote.RetrofitInstance
import com.example.pokedex_chg.domains.models.Pokemon
import com.example.pokedex_chg.domains.models.ReducedPokemonData
import com.example.pokedex_chg.domains.repositories.PokemonRepository
import com.example.pokedex_chg.mappers.mapToPokemon
import org.json.simple.JSONObject
import java.util.Locale
import javax.inject.Inject

class PokemonAPIRepositoryImpl @Inject constructor(): PokemonRepository {

    private val apiService = RetrofitInstance.api

    override suspend fun getAllPokemons(): List<ReducedPokemonData> {
        val response = apiService.getAllPokemons()
        return response.pokemon_entries.map { entry ->
            val pokemonId = entry.entry_number.toString()
            val pokemonName = entry.pokemon_species.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
            val pokemonPhotoUrl = getPokemonPhotoUrl(entry.entry_number)

            ReducedPokemonData(pokemonId, pokemonName, pokemonPhotoUrl)
        }
    }

    override fun getPokemonByArchive(name: String): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        val pokemonDTO = apiService.getPokemonByName(name)
        return mapToPokemon(pokemonDTO)
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        val pokemonDTO = apiService.getPokemonById(id.toString())
        return mapToPokemon(pokemonDTO)
    }


    private fun getPokemonPhotoUrl(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }


}
