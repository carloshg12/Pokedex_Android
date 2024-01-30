package com.example.pokedex_chg.di

import android.app.Application
import com.example.pokedex_chg.data.repositories.PokemonAPIRepositoryImpl
import com.example.pokedex_chg.data.repositories.PokemonFallbackRepositoryImpl
import com.example.pokedex_chg.data.sources.local.PokemonGson
import com.example.pokedex_chg.data.sources.remote.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): PokemonApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokemonApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepositoryImpl(): PokemonAPIRepositoryImpl {
        return PokemonAPIRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideJsonRepositoryImpl(application: Application): PokemonGson {
        return PokemonGson(application)
    }

    @Provides
    @Singleton
    fun provideFallBackRepositoryImpl(apiRepositoryImpl: PokemonAPIRepositoryImpl, jsonRepositoryImpl: PokemonGson): PokemonFallbackRepositoryImpl {
        return PokemonFallbackRepositoryImpl(jsonRepositoryImpl,apiRepositoryImpl)
    }
}