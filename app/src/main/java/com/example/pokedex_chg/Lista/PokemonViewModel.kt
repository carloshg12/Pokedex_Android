package com.example.pokedex_chg.Lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class PokemonViewModel(private val repository: PokemonRepository = PokemonRepositoryImpl()) : ViewModel() {

    val pokemons = liveData {
        emit(repository.getAllPokemons())
    }
}
