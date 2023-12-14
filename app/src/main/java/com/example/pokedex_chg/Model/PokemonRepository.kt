package com.example.pokedex_chg.Model

import org.json.simple.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.InputStream

interface PokemonRepository {
    fun getPokemonByArchive(jsonObject: JSONObject): Pokemon
    suspend fun getPokemonByName(name: String): Pokemon

    suspend fun getPokemonById(id: Int) : Pokemon




}