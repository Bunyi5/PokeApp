package com.example.pokeapp.pokemon.recycle.helper

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.pokeapp.database.entity.Pokemon

@BindingAdapter("pokemonNameText")
fun TextView.setPokemonNameText(item: Pokemon?) {
    item?.let {
        text = item.pokeName
    }
}

@BindingAdapter("pokemonApiId")
fun TextView.setPokemonApiId(item: Pokemon?) {
    item?.let {
        text = item.apiId.toString()
    }
}