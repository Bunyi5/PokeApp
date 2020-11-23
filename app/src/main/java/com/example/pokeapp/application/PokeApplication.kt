package com.example.pokeapp.application

import android.app.Application
import timber.log.Timber

class PokeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}