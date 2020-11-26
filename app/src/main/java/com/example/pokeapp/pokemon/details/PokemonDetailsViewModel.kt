package com.example.pokeapp.pokemon.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.database.PokemonDatabase
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(application: Application, val pokemonName: String) : AndroidViewModel(application) {

    private val database = PokemonDatabase.getInstance(application, viewModelScope).pokemonDatabaseDao

    private var _pokemonId = MutableLiveData<Long>()
    val pokemonId: LiveData<Long>
        get() = _pokemonId

    init {
        setPokemonId()
    }

    private fun setPokemonId() {
        viewModelScope.launch {
            _pokemonId.value = database.getPokemon(pokemonName)?.id
        }
    }
}