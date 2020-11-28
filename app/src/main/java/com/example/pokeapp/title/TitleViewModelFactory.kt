package com.example.pokeapp.title

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

class TitleViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TitleViewModel::class.java)) {
            Timber.i("TitleViewModelFactory created!")
            return TitleViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}