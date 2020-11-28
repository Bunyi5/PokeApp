package com.example.pokeapp.pokemon.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.database.entity.Pokemon
import com.example.pokeapp.database.PokemonDatabase
import timber.log.Timber

class PokemonListViewModel(application: Application) : AndroidViewModel(application) {

    private val database = PokemonDatabase.getInstance(application, viewModelScope).pokemonDatabaseDao

    private var _pokeNames = database.getAllPokemon()
    val pokeNames: LiveData<List<Pokemon>>
        get() = _pokeNames

    init {
        Timber.i("PokemonListViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("PokemonListViewModel destroyed!")
    }
}