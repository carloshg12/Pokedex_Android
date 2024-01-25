package com.example.pokedex_chg.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex_chg.data.models.ReducedPokemonData
import com.example.pokedex_chg.data.repositories.PokemonRepository
import com.example.pokedex_chg.data.repositories.PokemonRepositoryImpl
import com.example.pokedex_chg.domains.models.Pokemon
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository = PokemonRepositoryImpl()) : ViewModel() {

    private val _pokemons = MutableLiveData<List<ReducedPokemonData>>()
    val pokemons: LiveData<List<ReducedPokemonData>> = _pokemons

    init {
        viewModelScope.launch {
            val pokemonList = repository.getAllPokemons()
            _pokemons.postValue(pokemonList)
        }
    }
}


