package com.example.pokedex_chg.Model

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.simple.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class PokemonApi : PokemonRepository {

    override fun getPokemonByArchive(jsonObject: JSONObject): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        return withContext(Dispatchers.IO) {
            val url = URL("https://pokeapi.co/api/v2/pokemon/$name")
            val connection = url.openConnection() as HttpURLConnection

            try {
                connection.requestMethod = "GET"
                connection.connect()

                if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                    throw RuntimeException("HTTP error code: ${connection.responseCode}")
                }

                val inputStream = connection.inputStream
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val gson = Gson()

                val gsonPokemon = gson.fromJson(jsonString, Pokemon_Serializable::class.java)

                // Transformación de gsonPokemon a tu modelo Pokemon
                transformToPokemonModel(gsonPokemon)
            } finally {
                connection.disconnect()
            }
        }
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        return withContext(Dispatchers.IO) {
            val url = URL("https://pokeapi.co/api/v2/pokemon/$id")
            val connection = url.openConnection() as HttpURLConnection

            try {
                connection.requestMethod = "GET"
                connection.connect()

                if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                    throw RuntimeException("HTTP error code: ${connection.responseCode}")
                }

                val inputStream = connection.inputStream
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val gson = Gson()

                val gsonPokemon = gson.fromJson(jsonString, Pokemon_Serializable::class.java)

                transformToPokemonModel(gsonPokemon)
            } finally {
                connection.disconnect()
            }
        }
    }

    private fun transformToPokemonModel(gsonPokemon: Pokemon_Serializable): Pokemon {
        val id = gsonPokemon.id.padStart(3, '0')
        val name = gsonPokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val weight = gsonPokemon.weight.toDouble() / 10 // asumiendo que el peso está en decagramos
        val height = gsonPokemon.height.toDouble() / 10 // asumiendo que la altura está en decímetros
        val experience = gsonPokemon.experience.toInt()
        val photo = gsonPokemon.sprites.other.officialArtwork.frontDefault

        val statsMap = gsonPokemon.stats.associate { it.stat.name to it.base_stat }
        val abilitiesList = gsonPokemon.types.map { it.type.name }

        return Pokemon(
            id,
            photo,
            name,
            abilitiesList,
            weight,
            height,
            statsMap["hp"] ?: 0,
            statsMap["attack"] ?: 0,
            statsMap["defense"] ?: 0,
            statsMap["speed"] ?: 0,
            experience
        )
    }
}