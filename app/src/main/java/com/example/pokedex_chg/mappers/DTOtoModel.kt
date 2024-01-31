package com.example.pokedex_chg.mappers

import com.example.pokedex_chg.data.dto.PokemonDTO
import com.example.pokedex_chg.domains.models.Pokemon
import java.util.Locale

fun mapToPokemon(pokemonSerializable: PokemonDTO): Pokemon {
    val id = pokemonSerializable.id.padStart(3, '0')
    val name =
        pokemonSerializable.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    val weight = pokemonSerializable.weight.toDouble()
    val height = pokemonSerializable.height.toDouble()
    val experience = pokemonSerializable.experience.toInt()
    val photo = pokemonSerializable.sprites.other.officialArtwork.frontDefault

    val statsMap = pokemonSerializable.stats.associate { it.stat.name to it.base_stat }

    val abilitiesList = pokemonSerializable.types.map { it.type.name }

    return Pokemon(
        id,
        photo,
        name,
        abilitiesList,
        weight / 10,
        height / 10,
        statsMap["hp"] ?: 0,
        statsMap["attack"] ?: 0,
        statsMap["defense"] ?: 0,
        statsMap["speed"] ?: 0,
        experience
    )
}