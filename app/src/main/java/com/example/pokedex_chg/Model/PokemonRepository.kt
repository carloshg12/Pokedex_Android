package com.example.pokedex_chg.Model

import org.json.simple.JSONObject
import java.io.InputStream

interface PokemonRepository {

    fun getPokemonByArchive(jsonObject: JSONObject): Pokemon

    suspend fun getPokemonByName(name: String): Pokemon

}