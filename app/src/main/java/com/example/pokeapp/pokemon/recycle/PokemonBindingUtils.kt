package com.example.pokeapp.pokemon.recycle

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.pokeapp.database.Pokemon

@BindingAdapter("pokemonNameText")
fun TextView.setPokemonNameText(item: Pokemon?) {
    item?.let {
        text = item.pokeName
    }
}

@BindingAdapter("pokemonUrlText")
fun TextView.setPokemonUrlText(item: Pokemon?) {
    item?.let {
        text = item.pokeUrl
    }
}