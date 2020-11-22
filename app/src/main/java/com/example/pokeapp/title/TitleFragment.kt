package com.example.pokeapp.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
                R.layout.fragment_title, container, false)

        binding.pokeNameButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToPokemonListFragment())
        }
        binding.pokeAbilitiesButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToBlankFragment())
        }
        binding.pokeTypesButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToBlankFragment())
        }
        binding.button5.setOnClickListener{ view : View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToBlankFragment())
        }
        binding.recycleViewButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToBlankFragment())
        }

        return binding.root
    }
}