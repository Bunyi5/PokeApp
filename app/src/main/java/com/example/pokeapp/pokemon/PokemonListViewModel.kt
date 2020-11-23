package com.example.pokeapp.pokemon

import androidx.lifecycle.ViewModel
import timber.log.Timber
import kotlin.random.Random

class PokemonListViewModel(private val pokeNameList: List<String>) : ViewModel() {

    lateinit var randomPokemonName: String

    init {
        setRandomPokemonName()
        Timber.i("PokemonListViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("PokemonListViewModel destroyed!")
    }

    private fun setRandomPokemonName() {
        randomPokemonName = pokeNameList[getRandomNumber()]
    }

    private fun getRandomNumber(): Int {
        return Random.nextInt(0, 3)
    }
}