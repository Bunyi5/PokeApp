package com.example.pokeapp.pokemon.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pokeapp.database.Pokemon
import timber.log.Timber
import kotlin.random.Random

class RandomPokemonViewModel(private val pokeNameList: List<Pokemon>) : ViewModel() {

    // TODO: 2020. 11. 24. Change list to database reference
    private val _randomPokemonName = MutableLiveData<String>()
    val randomPokemonName: LiveData<String>
        get() = _randomPokemonName

    val randomPokemonHex = Transformations.map(randomPokemonName) { text ->
        text.toByteArray().toHexString()
    }

    init {
        _randomPokemonName.value = ""
        Timber.i("RandomPokemonViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("RandomPokemonViewModel destroyed!")
    }

    fun setRandomPokemonName() {
        _randomPokemonName.value = pokeNameList[getRandomNumber()].name
    }

    private fun getRandomNumber(): Int {
        return Random.nextInt(0, pokeNameList.size)
    }

    private fun ByteArray.toHexString() : String {
        return this.joinToString("") {
            java.lang.String.format("%02x", it)
        }
    }
}