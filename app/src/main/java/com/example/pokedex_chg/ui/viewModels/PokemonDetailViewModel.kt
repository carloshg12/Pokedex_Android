package com.example.pokedex_chg.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pokedex_chg.data.repositories.PokemonRepository
import com.example.pokedex_chg.data.repositories.PokemonRepositoryImpl
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository = PokemonRepositoryImpl()) : ViewModel() {

    val pokemons = liveData {
        emit(repository.getAllPokemons())
    }
}
