package com.example.pokeapp.pokemon.list

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentPokemonListBinding

/**
 * A simple [Fragment] subclass.
 */
class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var viewModelFactory: PokemonListViewModelFactory

    private var pokemonNames: MutableList<String> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        viewModel.pokeNames.observe(viewLifecycleOwner, Observer { pokemonList ->
            pokemonNames.clear()
            pokemonList.forEach { pokemon -> pokemonNames.add(pokemon.pokeName) }
            handleClick(binding.pokemonNames)
        })

        return binding.root
    }

    private fun handleClick(layout: LinearLayout) {
        layout.visibility = View.VISIBLE
        pokemonNames.forEach { pokemon ->
            val button = Button(this.context)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            button.setBackgroundColor(Color.TRANSPARENT)
            button.text = pokemon
            button.setTextColor(Color.YELLOW)
            button.setOnClickListener {
                this.findNavController().navigate(
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                        pokemon
                    )
                )
            }
            layout.addView(button)
        }
    }
}