package com.example.pokeapp.pokemon.recycle.helper

import com.example.pokeapp.database.entity.Pokemon

class PokemonListener(val clickListener: (pokeId: Long) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon.id)
}