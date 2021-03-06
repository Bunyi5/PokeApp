package com.example.pokeapp.pokemon.recycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentRecycleBinding
import com.example.pokeapp.pokemon.recycle.helper.PokemonAdapter
import com.example.pokeapp.pokemon.recycle.helper.PokemonListener

class RecycleFragment : Fragment() {

    private lateinit var binding: FragmentRecycleBinding

    private lateinit var viewModel: RecycleViewModel
    private lateinit var viewModelFactory: RecycleViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recycle,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        viewModelFactory = RecycleViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecycleViewModel::class.java)

        val adapter = PokemonAdapter(PokemonListener { pokeId ->
            viewModel.onPokemonClicked(pokeId)
        })

        binding.pokemonList.adapter = adapter

        viewModel.pokemons.observe(viewLifecycleOwner, {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        viewModel.navigateToPokemonDetails.observe(viewLifecycleOwner, { pokeId ->
            pokeId?.let {
                this.findNavController().navigate(
                    RecycleFragmentDirections.actionRecycleFragmentToPokemonDetailsFragment(pokeId)
                )
                viewModel.onPokemonDetailsNavigated()
            }
        })

        return binding.root
    }

}