package com.example.pokeapp.network

import com.example.pokeapp.database.entity.PokemonList
import com.example.pokeapp.network.details.PokemonDetails
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PokemonApiService {
    @GET("pokemon?limit=100")
    suspend fun getPokemons(): PokemonList

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: String?): PokemonDetails
}

object PokeApi {
    val retrofitService: PokemonApiService by lazy { retrofit.create(PokemonApiService::class.java) }
}