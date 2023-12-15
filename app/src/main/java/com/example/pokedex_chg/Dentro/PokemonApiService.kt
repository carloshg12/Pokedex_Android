package com.example.pokedex_chg.Dentro

import com.example.pokedex_chg.domains.models.Pokemon_Serializable
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
        @GET("pokemon/{id}")
        suspend fun getPokemonById(@Path("id") id: String): Pokemon_Serializable
    }
