package com.example.pokedex_chg.Model

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import java.io.InputStream

class PokemonJson : PokemonRepository {

    override fun getPokemonByArchive(jsonObject: JSONObject): Pokemon {

        var hp = 0
        var attack = 0
        var defense = 0
        var speed = 0

        val number = (jsonObject["id"] as Long).toString().padStart(3,'0')

        val spritesJson = jsonObject["sprites"] as JSONObject
        val otherJson = spritesJson["other"] as JSONObject
        val official = otherJson["official-artwork"] as JSONObject
        val photo = official["front_default"] as String

        val name = jsonObject["name"] as String

        val abilitiesArray = jsonObject["types"] as JSONArray
        val abilitiesList = mutableListOf<String>()

        for (i in 0 until abilitiesArray.size) {
            val abilityObj = abilitiesArray[i] as JSONObject
            val typeName = (abilityObj["type"] as JSONObject)["name"] as String
            abilitiesList.add(typeName)
        }


        val weightValue = (jsonObject["weight"] as Long).toDouble()
        val heightValue = (jsonObject["height"] as Long).toDouble()

        val weight = String.format("%.1f", weightValue / 10.0).toDouble()
        val height = String.format("%.1f", heightValue / 10.0).toDouble()

        val statsArray = jsonObject["stats"] as JSONArray

        for (i in 0 until statsArray.size) {
            val statObj = statsArray[i] as JSONObject
            val baseStat = statObj["base_stat"] as Long
            val statName = (statObj["stat"] as JSONObject)["name"] as String

            when (statName) {
                "hp" -> hp = baseStat.toInt()
                "attack" -> attack = baseStat.toInt()
                "defense" -> defense = baseStat.toInt()
                "speed" -> speed = baseStat.toInt()
            }
        }

        val experience = (jsonObject["base_experience"] as Long).toInt()

        return Pokemon(
            number,
            photo,
            name,
            abilitiesList,
            weight,
            height,
            hp,
            attack,
            defense,
            speed,
            experience
        )
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        TODO("Not yet implemented")
    }
}
