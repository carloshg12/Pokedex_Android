package com.example.pokedex_chg.data.sources.local

import com.example.pokedex_chg.domains.models.Pokemon
import com.example.pokedex_chg.domains.models.Pokemon_Serializable
import com.example.pokedex_chg.domains.repositories.PokemonRepository
import com.google.gson.Gson
import org.json.simple.JSONObject


class PokemonGson : PokemonRepository {
    override fun getPokemonByArchive(jsonObject: JSONObject): Pokemon {

        val jsonString = jsonObject.toString()

        val gson = Gson()

        val gsonPokemon = gson.fromJson(jsonString, Pokemon_Serializable::class.java)

        val id = gsonPokemon.id.padStart(3, '0')
        val name = gsonPokemon.name.capitalize()
        val weight = gsonPokemon.weight.toDouble()
        val height = gsonPokemon.height.toDouble()
        val experience = gsonPokemon.experience.toInt()
        val photo = gsonPokemon.sprites.other.officialArtwork.frontDefault

        val statsMap = gsonPokemon.stats.associate { it.stat.name to it.base_stat }

        val abilitiesList = gsonPokemon.types.map { it.type.name }

        return Pokemon(
            id,
            photo,
            name,
            abilitiesList,
            weight/10,
            height/10,
            statsMap["hp"] ?: 0,
            statsMap["attack"] ?: 0,
            statsMap["defense"] ?: 0,
            statsMap["speed"] ?: 0,
            experience ?:0
        )
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        TODO("Not yet implemented")
    }
}