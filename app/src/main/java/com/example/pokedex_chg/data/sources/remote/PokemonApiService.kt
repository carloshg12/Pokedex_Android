package com.example.pokedex_chg.data.sources.remote

import com.example.pokedex_chg.data.models.PokemonResponse
import com.example.pokedex_chg.domains.models.Pokemon_Serializable
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
        @GET("pokemon/{id}")
        suspend fun getPokemonById(@Path("id") id: String): Pokemon_Serializable

    @GET("1/")
    suspend fun getAllPokemons(): PokemonResponse

}
