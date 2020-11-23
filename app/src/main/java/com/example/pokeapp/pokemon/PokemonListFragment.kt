package com.example.pokeapp.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentPokemonListBinding

/**
 * A simple [Fragment] subclass.
 */
class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var viewModelFactory: PokemonListViewModelFactory
    private var pokeNameList : List<String> = listOf("ditto", "charmander", "pikachu")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_list, container, false)

        viewModelFactory = PokemonListViewModelFactory(pokeNameList)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonListViewModel::class.java)

        binding.button.setOnClickListener{ view : View ->
            view.findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(viewModel.randomPokemonName))
        }
        updateButtonText()

        return binding.root
    }

    private fun updateButtonText() {
        binding.button.text = viewModel.randomPokemonName
    }

}