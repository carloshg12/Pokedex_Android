package com.example.pokedex_chg.di

import com.example.pokedex_chg.data.repositories.PokemonFallbackRepositoryImpl
import com.example.pokedex_chg.domains.repositories.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokemonRepositoryModule {

    @Binds
    abstract fun bindRepository(
        pokemonFallbackRepositoryImpl: PokemonFallbackRepositoryImpl
    ): PokemonRepository
}