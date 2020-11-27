package com.example.pokeapp.pokemon.random

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

class RandomPokemonViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomPokemonViewModel::class.java)) {
            Timber.i("RandomPokemonViewModelFactory created!")
            return RandomPokemonViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}