package com.example.pokedex_chg.Lista

import retrofit2.http.GET

interface PokemonApiService {
    @GET("1/")
    suspend fun getAllPokemons(): PokemonResponse
}
