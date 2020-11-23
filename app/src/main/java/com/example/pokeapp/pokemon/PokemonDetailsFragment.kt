package com.example.pokeapp.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentPokemonDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class PokemonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pokemon_details, container, false
        )

        val args = PokemonDetailsFragmentArgs.fromBundle(requireArguments())
        binding.textView.text = args.pokemonName

        return binding.root
    }
}