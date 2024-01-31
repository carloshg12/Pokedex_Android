package com.example.pokedex_chg.data.sources.remote

import com.example.pokedex_chg.domains.models.PokemonEntryDTO

data class PokemonEntriesResponse(
    val pokemon_entries: List<PokemonEntryDTO>
)

