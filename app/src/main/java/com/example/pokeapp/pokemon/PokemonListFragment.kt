package com.example.pokeapp.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentPokemonListBinding

/**
 * A simple [Fragment] subclass.
 */
class PokemonListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPokemonListBinding>(inflater,
            R.layout.fragment_pokemon_list, container, false)

        val pokemonName = "Ditto"

        binding.button.setOnClickListener{ view : View ->
            view.findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(pokemonName))
        }

        return binding.root
    }

}