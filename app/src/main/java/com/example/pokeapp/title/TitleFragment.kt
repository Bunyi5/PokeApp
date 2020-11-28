package com.example.pokeapp.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding

    private lateinit var viewModel: TitleViewModel
    private lateinit var viewModelFactory: TitleViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        viewModelFactory = TitleViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TitleViewModel::class.java)

        binding.pokeNameButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToPokemonListFragment())
        }
        binding.randomPokeButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToRandomPokemonFragment())
        }
        binding.recycleViewButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToRecycleFragment())
        }

        return binding.root
    }
}