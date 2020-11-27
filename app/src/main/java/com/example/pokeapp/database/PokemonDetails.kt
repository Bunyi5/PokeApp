package com.example.pokeapp.database

import com.squareup.moshi.Json

data class PokemonDetails(
    @Json(name = "name")
    var pokeName: String = "",

    var abilities: List<Ability>
)

data class Ability(
    var ability: AbilityName
)

data class AbilityName(
    @Json(name = "name")
    var abilityName: String = ""
)