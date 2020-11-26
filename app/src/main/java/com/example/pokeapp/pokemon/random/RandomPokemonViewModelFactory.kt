package com.example.pokeapp.pokemon.random

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.database.PokemonDatabaseDao
import timber.log.Timber

class RandomPokemonViewModelFactory(
    private val dataSource: PokemonDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomPokemonViewModel::class.java)) {
            Timber.i("RandomPokemonViewModelFactory created!")
            return RandomPokemonViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}