package com.example.pokedex_chg.mappers

import com.example.pokedex_chg.domains.models.PokemonEntryDTO
import com.example.pokedex_chg.domains.models.ReducedPokemonData

fun mapPokemonEntryToReducedData(entry: PokemonEntryDTO): ReducedPokemonData {
    return ReducedPokemonData(
        id = entry.entry_number.toString(),
        name = entry.pokemon_species.name,
        photo = "no_foto"
    )
}
