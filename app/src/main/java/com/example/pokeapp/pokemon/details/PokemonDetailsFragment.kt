package com.example.pokeapp.pokemon.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentPokemonDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class PokemonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding

    private lateinit var viewModel: PokemonDetailsViewModel
    private lateinit var viewModelFactory: PokemonDetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pokemon_details,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val args = PokemonDetailsFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = PokemonDetailsViewModelFactory(application, args.pokemonId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonDetailsViewModel::class.java)

        binding.pokemonDetailsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.pokemonDetails.observe(viewLifecycleOwner, { pokemonDetails ->
            binding.pokemonName.text = pokemonDetails.pokeName
            binding.pokemonHeight.text = pokemonDetails.height.toString()
            binding.pokemonWeight.text = pokemonDetails.weight.toString()

            pokemonDetails.abilities.forEach {
                drawList(binding.abilitiesList, it.ability.abilityName)
            }

            pokemonDetails.moves.forEach {
                drawList(binding.movesList, it.move.moveName)
            }
        })

        return binding.root
    }

    private fun drawList(layout: LinearLayout, abilityName: String) {
        val textView = TextView(this.context)
        textView.text = abilityName
        layout.addView(textView)
    }
}