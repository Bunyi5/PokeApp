package com.example.pokeapp.pokemon.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.database.Pokemon
import com.example.pokeapp.database.PokemonDatabaseDao
import kotlinx.coroutines.launch
import timber.log.Timber

class PokemonListViewModel(val database: PokemonDatabaseDao, application: Application) : AndroidViewModel(application) {

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

    // TODO: 2020. 11. 26. Delete editDatabase in the future
    fun editDatabase() {
        viewModelScope.launch {
            clearDatabase()
            insertIntoDatabase()
        }
    }

    private suspend fun clearDatabase() {
        database.clear()
    }

    private suspend fun insertIntoDatabase() {
        val pokemon1 = Pokemon()
        val pokemon2 = Pokemon()

        pokemon1.pokeName = "charmander"
        pokemon2.pokeName = "ditto"

        database.insertAll(listOf(pokemon1, pokemon2))
    }
}