package com.example.pokeapp.pokemon.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.database.PokemonDatabaseDao
import timber.log.Timber

class PokemonDetailsViewModelFactory(
    private val dataSource: PokemonDatabaseDao,
    private val application: Application,
    private val pokemonName: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailsViewModel::class.java)) {
            Timber.i("PokemonDetailsViewModelFactory created!")
            return PokemonDetailsViewModel(dataSource, application, pokemonName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}