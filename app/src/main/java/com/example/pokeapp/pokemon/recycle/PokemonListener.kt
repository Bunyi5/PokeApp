package com.example.pokeapp.pokemon.recycle

import com.example.pokeapp.database.Pokemon

class PokemonListener(val clickListener: (pokeId: Long) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon.id)
}