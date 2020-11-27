package com.example.pokeapp.pokemon.details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pokemon_details,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val args = PokemonDetailsFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = PokemonDetailsViewModelFactory(application, args.pokemonName)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonDetailsViewModel::class.java)

        binding.pokemonDetailsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.pokemonAbilities.observe(viewLifecycleOwner, Observer { abilityList ->
            abilityList.forEach {
                drawAbilities(binding.abilitiesList, it.ability.abilityName)
            }
        })

        return binding.root
    }

    private fun drawAbilities(layout: LinearLayout, abilityName: String) {
        layout.visibility = View.VISIBLE
        val textView = TextView(this.context)
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = abilityName
        textView.setTextColor(Color.YELLOW)
        layout.addView(textView)
    }
}