package com.example.pokedex_chg.data.sources.remote

import com.example.pokedex_chg.data.dto.PokemonDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: String): PokemonDTO

    @GET("pokedex/1/")
    suspend fun getAllPokemons(): PokemonEntriesResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDTO

}

