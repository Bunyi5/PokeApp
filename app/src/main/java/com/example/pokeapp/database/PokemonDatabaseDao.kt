package com.example.pokeapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PokemonDatabaseDao {

    @Insert
    suspend fun insert(pokemon: Pokemon)

    @Insert
    suspend fun insertAll(pokemonList: List<Pokemon>)

    @Update
    suspend fun update(pokemon: Pokemon)

    @Query("SELECT * from pokemon_table WHERE pokemon_name = :name")
    suspend fun getPokemon(name: String): Pokemon?

    @Query("SELECT * from pokemon_table WHERE id = :key")
    suspend fun getPokemonById(key: Long): Pokemon?

    @Query("SELECT * FROM pokemon_table")
    fun getAllPokemon(): LiveData<List<Pokemon>>

    @Query("DELETE FROM pokemon_table")
    suspend fun clear()

    @Query("SELECT * FROM pokemon_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomPokemon(): Pokemon?
}