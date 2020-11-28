package com.example.pokeapp.title

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.database.PokemonDatabase
import kotlinx.coroutines.launch

class TitleViewModel(application: Application) : AndroidViewModel(application) {
    private var database = PokemonDatabase.getInstance(application, viewModelScope).pokemonDatabaseDao

    init {
        viewModelScope.launch {
            database.getTopPokemon()
        }
    }

}