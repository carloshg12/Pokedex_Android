package com.example.pokedex_chg.domains.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pokemon(
    val number: String,
    val photo: String,
    val name: String,
    val abilities: List<String>,
    val weight: Double,
    val height: Double,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val experience: Int
)

