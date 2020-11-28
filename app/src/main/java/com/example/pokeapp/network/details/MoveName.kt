package com.example.pokeapp.network.details

import com.squareup.moshi.Json

data class MoveName(
    @Json(name = "name")
    var moveName: String
)