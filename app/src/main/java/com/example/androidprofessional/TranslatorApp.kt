package com.example.androidprofessional

import android.app.Application
import com.example.androidprofessional.di.koin.*
import com.facebook.stetho.Stetho
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen, favouriteScreen, memoryCardsScreen))
        }
        Stetho.initializeWithDefaults(this)
    }
}