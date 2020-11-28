package com.example.pokeapp.pokemon.recycle.helper

import com.example.pokeapp.database.entity.Pokemon

sealed class DataItem {
    abstract val id: Long

    data class PokemonItem(val pokemon: Pokemon) : DataItem() {
        override val id = pokemon.id
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }
}