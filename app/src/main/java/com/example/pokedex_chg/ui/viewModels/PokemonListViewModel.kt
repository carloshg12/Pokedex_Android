package com.example.pokedex_chg.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_chg.domains.models.ReducedPokemonData
import com.example.pokedex_chg.domains.usecases.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _pokemons = MutableLiveData<List<ReducedPokemonData>>()
    val pokemons: LiveData<List<ReducedPokemonData>> = _pokemons

    init {
        viewModelScope.launch {
            val pokemonList = pokemonUseCase.getAllPokemons()
            _pokemons.postValue(pokemonList)
        }
    }
}


