package com.example.pokeapp.pokemon.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

class PokemonDetailsViewModelFactory(
    private val application: Application,
    private val pokemonName: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailsViewModel::class.java)) {
            Timber.i("PokemonDetailsViewModelFactory created!")
            return PokemonDetailsViewModel(application, pokemonName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}