package com.example.pokeapp.pokemon.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.database.Pokemon
import timber.log.Timber

// TODO: 2020. 11. 24. Change list to database reference
class PokemonListViewModel(pokeNameList: List<Pokemon>) : ViewModel() {

    // TODO: 2020. 11. 24. Change list to database reference
    private val _pokeNames = MutableLiveData<List<Pokemon>>()
    val pokeNames: LiveData<List<Pokemon>>
        get() = _pokeNames

    init {
        _pokeNames.value = pokeNameList
        Timber.i("PokemonListViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("PokemonListViewModel destroyed!")
    }
}