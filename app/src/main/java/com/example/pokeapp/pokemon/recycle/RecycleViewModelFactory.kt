package com.example.pokeapp.pokemon.recycle

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

class RecycleViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecycleViewModel::class.java)) {
            Timber.i("RecycleViewModel created!")
            return RecycleViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}