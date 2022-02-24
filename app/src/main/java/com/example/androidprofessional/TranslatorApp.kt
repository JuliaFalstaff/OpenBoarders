package com.example.androidprofessional

import android.app.Application
import com.example.androidprofessional.di.koin.application
import com.example.androidprofessional.di.koin.favouriteScreen
import com.example.androidprofessional.di.koin.historyScreen
import com.example.androidprofessional.di.koin.mainScreen
import com.example.androidprofessional.utils.ExoPlayerProvider
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen, favouriteScreen))
        }
        Stetho.initializeWithDefaults(this)
        ExoPlayerProvider.create(this)
    }
}