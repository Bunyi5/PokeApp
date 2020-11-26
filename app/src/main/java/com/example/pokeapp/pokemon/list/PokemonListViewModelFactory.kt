package com.example.pokeapp.pokemon.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

class PokemonListViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java)) {
            Timber.i("PokemonListViewModelFactory created!")
            return PokemonListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}