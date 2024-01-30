package com.example.pokedex_chg.domains.usecases

import com.example.pokedex_chg.domains.models.Pokemon
import com.example.pokedex_chg.domains.models.ReducedPokemonData
import com.example.pokedex_chg.domains.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
){
    suspend fun getPokemonById(id: Int): Pokemon {
        return repository.getPokemonById(id)
    }
}

