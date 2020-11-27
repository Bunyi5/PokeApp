package com.example.pokeapp.pokemon.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.database.PokemonDatabase
import com.example.pokeapp.database.PokemonDetails
import com.example.pokeapp.network.PokeApi
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(application: Application, var pokeId: Long) : AndroidViewModel(application) {

    private val database = PokemonDatabase.getInstance(application, viewModelScope).pokemonDatabaseDao

    private var _pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonDetails: LiveData<PokemonDetails>
        get() = _pokemonDetails

    init {
        setPokemonDetails()
    }

    private fun setPokemonDetails() {
        viewModelScope.launch {
            val id = getPokemonApiId()
            if (id != null) {
                val pokemonDetails: PokemonDetails = PokeApi.retrofitService.getPokemonDetails(id)
                _pokemonDetails.value = pokemonDetails
            }
        }
    }

    private suspend fun getPokemonApiId(): String? {
        val pokeUrl = database.getPokemonById(pokeId)?.pokeUrl
        return pokeUrl?.substring(34, pokeUrl.length - 1)
    }
}