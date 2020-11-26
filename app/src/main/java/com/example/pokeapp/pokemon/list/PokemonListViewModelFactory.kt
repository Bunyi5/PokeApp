package com.example.pokeapp.pokemon.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.database.PokemonDatabaseDao
import timber.log.Timber

class PokemonListViewModelFactory(
    private val dataSource: PokemonDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java)) {
            Timber.i("PokemonListViewModelFactory created!")
            return PokemonListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}