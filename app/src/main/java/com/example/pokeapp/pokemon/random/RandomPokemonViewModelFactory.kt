package com.example.pokeapp.pokemon.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.database.Pokemon
import timber.log.Timber

// TODO: 2020. 11. 25. Change list to database reference
class RandomPokemonViewModelFactory(private val pokeNameList: List<Pokemon>) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomPokemonViewModel::class.java)) {
            Timber.i("RandomPokemonViewModelFactory created!")
            return RandomPokemonViewModel(pokeNameList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}