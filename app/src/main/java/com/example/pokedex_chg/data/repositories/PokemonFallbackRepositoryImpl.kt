package com.example.pokedex_chg.data.repositories

import android.util.Log
import com.example.pokedex_chg.data.sources.local.PokemonGson
import com.example.pokedex_chg.domains.models.Pokemon
import com.example.pokedex_chg.domains.models.ReducedPokemonData
import com.example.pokedex_chg.domains.repositories.PokemonRepository
import org.json.simple.JSONObject
import javax.inject.Inject

class PokemonFallbackRepositoryImpl @Inject constructor(
    private val pokemonGSON: PokemonGson,
    private val pokemonAPIRepositoryImpl: PokemonAPIRepositoryImpl
): PokemonRepository {

    override suspend fun getPokemonById(id: Int): Pokemon {
        return try {
            pokemonAPIRepositoryImpl.getPokemonById(id)
        }catch (e: Exception) {
            return pokemonGSON.getPokemonByArchive("charizard")
        }
    }

    override suspend fun getAllPokemons(): List<ReducedPokemonData> {
        return try {
            pokemonAPIRepositoryImpl.getAllPokemons()
        }catch (e: Exception) {
            pokemonGSON.getAllPokemons()
        }
    }

    override fun getPokemonByArchive(name: String): Pokemon {
        TODO("Not yet implemented")
    }


    override suspend fun getPokemonByName(name: String): Pokemon {
        TODO("Not yet implemented")
    }

}