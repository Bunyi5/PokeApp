package com.example.pokeapp.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

class PokemonListViewModelFactory(private val pokeNameList: List<String>) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java)) {
            Timber.i("PokemonListViewModelFactory created!")
            return PokemonListViewModel(pokeNameList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}