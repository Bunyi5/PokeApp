package com.example.pokeapp.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.database.Pokemon
import timber.log.Timber

// TODO: 2020. 11. 24. Change list to database reference
class PokemonListViewModelFactory(private val pokeNameList: List<Pokemon>) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java)) {
            Timber.i("PokemonListViewModelFactory created!")
            return PokemonListViewModel(pokeNameList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}