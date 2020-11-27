package com.example.pokeapp.database

import com.squareup.moshi.Json

data class PokemonDetails(
    @Json(name = "name")
    var pokeName: String,

    var height: Int,

    var weight: Int,

    var abilities: List<Ability>,

    var moves: List<Moves>
)

data class Ability(
    var ability: AbilityName
)

data class AbilityName(
    @Json(name = "name")
    var abilityName: String
)

data class Moves(
    var move: MoveName
)

data class MoveName(
    @Json(name = "name")
    var moveName: String
)