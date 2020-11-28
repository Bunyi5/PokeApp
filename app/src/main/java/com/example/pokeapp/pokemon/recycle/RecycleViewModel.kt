package com.example.pokeapp.pokemon.recycle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.database.PokemonDatabase
import com.example.pokeapp.database.entity.Pokemon

class RecycleViewModel(application: Application) : AndroidViewModel(application) {

    private var database = PokemonDatabase.getInstance(application, viewModelScope).pokemonDatabaseDao

    private var _pokemons: LiveData<List<Pokemon>> = database.getAllPokemon()
    val pokemons: LiveData<List<Pokemon>>
        get() = _pokemons

    private var _navigateToPokemonDetails = MutableLiveData<Long>()
    val navigateToPokemonDetails: LiveData<Long>
        get() = _navigateToPokemonDetails

    fun onPokemonClicked(id: Long) {
        _navigateToPokemonDetails.value = id
    }

    fun onPokemonDetailsNavigated() {
        _navigateToPokemonDetails.value = null
    }

}