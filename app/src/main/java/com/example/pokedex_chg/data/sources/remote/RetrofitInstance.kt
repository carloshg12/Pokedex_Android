package com.example.pokedex_chg.data.sources.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokedex/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}
