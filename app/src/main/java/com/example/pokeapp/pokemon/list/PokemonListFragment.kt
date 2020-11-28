package com.example.pokeapp.pokemon.list

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.database.Pokemon
import com.example.pokeapp.databinding.FragmentPokemonListBinding

/**
 * A simple [Fragment] subclass.
 */
class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var viewModelFactory: PokemonListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pokemon_list,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        viewModelFactory = PokemonListViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonListViewModel::class.java)

        binding.pokemonListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.pokeNames.observe(viewLifecycleOwner, { pokemonList ->
            pokemonList.forEach { pokemon ->
                handleClick(binding.pokemonNames, pokemon)
            }
        })

        return binding.root
    }

    private fun handleClick(layout: LinearLayout, pokemon: Pokemon) {
        val textView = TextView(this.context)
        textView.text = pokemon.pokeName
        textView.textSize = 24F
        textView.setTextColor(ResourcesCompat.getColor(resources, R.color.yellow, null))
        textView.gravity = Gravity.CENTER
        textView.setPadding(15, 15, 15,15)
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }
        textView.setOnClickListener {
            this.findNavController().navigate(
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                    pokemon.id
                )
            )
        }
        layout.addView(textView)
    }
}