package com.example.pokedex_chg.domains.usecases

import com.example.pokedex_chg.domains.models.ReducedPokemonData
import com.example.pokedex_chg.domains.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
){
    suspend fun getAllPokemons():List<ReducedPokemonData>{
        return repository.getAllPokemons()
    }
}