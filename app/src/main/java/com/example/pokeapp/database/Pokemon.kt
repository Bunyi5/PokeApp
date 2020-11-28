package com.example.pokeapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "pokemon_table")
data class Pokemon(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    var apiId: Long = 0L,

    @ColumnInfo(name = "pokemon_name")
    @Json(name = "name")
    var pokeName: String = "",

    @ColumnInfo(name = "pokemon_url")
    @Json(name = "url")
    var pokeUrl: String = ""
)