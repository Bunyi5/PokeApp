package com.example.pokeapp.pokemon.random

import android.app.Application
import androidx.lifecycle.*
import com.example.pokeapp.database.PokemonDatabaseDao
import kotlinx.coroutines.launch
import timber.log.Timber

class RandomPokemonViewModel(val database: PokemonDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _randomPokemonName = MutableLiveData<String?>()
    val randomPokemonName: LiveData<String?>
        get() = _randomPokemonName

    val randomPokemonHex = Transformations.map(randomPokemonName) { text ->
        text?.toByteArray()?.toHexString()
    }

    init {
        _randomPokemonName.value = ""
        Timber.i("RandomPokemonViewModel created!")
    }

    fun setRandomPokemonName() {
        viewModelScope.launch {
            _randomPokemonName.value = database.getRandomPokemon()?.pokeName
        }
    }

    private fun ByteArray.toHexString() : String {
        return this.joinToString("") {
            java.lang.String.format("%02x", it)
        }
    }
}