package com.example.pokeapp.network.details

import com.squareup.moshi.Json

data class AbilityName(
    @Json(name = "name")
    var abilityName: String
)