package com.example.pokeapp.pokemon.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.database.Ability
import com.example.pokeapp.database.PokemonDatabase
import com.example.pokeapp.database.PokemonDetails
import com.example.pokeapp.network.PokeApi
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(application: Application, var pokeId: Long) : AndroidViewModel(application) {

    private val database = PokemonDatabase.getInstance(application, viewModelScope).pokemonDatabaseDao

    private var _pokemonId = MutableLiveData<Long>()
    val pokemonId: LiveData<Long>
        get() = _pokemonId

    private var _pokemonName = MutableLiveData<String>()
    val pokemonName: LiveData<String>
        get() = _pokemonName

    private var _pokemonAbilities = MutableLiveData<List<Ability>>()
    val pokemonAbilities: LiveData<List<Ability>>
        get() = _pokemonAbilities

    init {
        setPokemonDetails()
    }

    private fun setPokemonDetails() {
        viewModelScope.launch {
            val id = getPokemonApiId()
            if (id != null) {
                val pokemonDetails: PokemonDetails = PokeApi.retrofitService.getPokemonDetails(id)
                _pokemonId.value = id.toLong()
                _pokemonName.value = pokemonDetails.pokeName
                _pokemonAbilities.value = pokemonDetails.abilities
            }
        }
    }

    private suspend fun getPokemonApiId(): String? {
        val pokeUrl = database.getPokemonById(pokeId)?.pokeUrl
        return pokeUrl?.substring(34, pokeUrl.length - 1)
    }
}