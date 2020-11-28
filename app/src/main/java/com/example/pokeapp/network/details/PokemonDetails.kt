package com.example.pokeapp.network.details

import com.squareup.moshi.Json

data class PokemonDetails(
    @Json(name = "name")
    var pokeName: String,

    var height: Int,

    var weight: Int,

    var abilities: List<Ability>,

    var moves: List<Moves>
)