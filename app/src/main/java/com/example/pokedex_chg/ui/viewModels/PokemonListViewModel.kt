package com.example.pokedex_chg.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.example.pokedex_chg.domains.models.Pokemon
import com.example.pokedex_chg.data.sources.remote.PokemonApi
import com.example.pokedex_chg.domains.models.PokemonColors
import kotlinx.coroutines.launch

class PokemonListViewModel(application: Application, private val pokemonParser: PokemonApi = PokemonApi()
) : AndroidViewModel(application) {

    private val _pokemon = MutableLiveData<Pokemon?>(null)
    val pokemon: LiveData<Pokemon?> = _pokemon

    var primaryColor by mutableStateOf(Color.Gray)

    fun getPokemonDetails(id: Int) {
        viewModelScope.launch {
            try {
                val pokemon = pokemonParser.getPokemonById(id)
                _pokemon.postValue(pokemon)
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }

    /*
    Funcion si se busca un pokemon por nombre
     */
    /*fun loadPokemonData(){//inputStream: InputStream) {
        val pokemonSearchedName = "charizard"
        viewModelScope.launch {
            try {
                val pokemon = pokemonParser.getPokemonByName(pokemonSearchedName)
                _pokemon.postValue(pokemon)

                /*
                * Si queremos leer mediante GSON un archivo json descomentamos estas lineas
                * y cambiamos en el constructor el parser
                */

                /*val jsonString = withContext(Dispatchers.IO) {
                    InputStreamReader(inputStream).use { it.readText() }
                }
                val jsonElement = JSONParser().parse(jsonString)

                val jsonObject = jsonElement as JSONObject
                val pokemon = pokemonParser.getPokemonByArchive(jsonObject)
                _pokemon.postValue(pokemon)*/

            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException("Error parsing JSON: ${e.message}")
            }
        }
    }*/

    fun getAbilityColor(ability: String): Color {
        return try {
            val enumColor = PokemonColors.valueOf(ability.uppercase())
            if (primaryColor == Color.Gray) {
                primaryColor = enumColor.color
            }
            enumColor.color
        } catch (e: IllegalArgumentException) {
            Color.Gray
        }
    }

}
