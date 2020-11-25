package com.example.pokeapp.pokemon.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.R
import com.example.pokeapp.database.Pokemon
import com.example.pokeapp.databinding.FragmentRandomPokemonBinding

class RandomPokemonFragment : Fragment() {

    private lateinit var binding: FragmentRandomPokemonBinding

    private lateinit var viewModel: RandomPokemonViewModel
    private lateinit var viewModelFactory: RandomPokemonViewModelFactory

    // TODO: 2020. 11. 25. Change list to database reference
    private var pokeNameList: List<Pokemon> =
        listOf(Pokemon("ditto"), Pokemon("charmander"), Pokemon("pikachu"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_random_pokemon,
            container,
            false
        )

        viewModelFactory = RandomPokemonViewModelFactory(pokeNameList)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RandomPokemonViewModel::class.java)

        binding.randomPokemonViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}