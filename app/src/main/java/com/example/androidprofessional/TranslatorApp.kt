package com.example.androidprofessional

import android.app.Application
import com.example.androidprofessional.di.koin.application
import com.example.androidprofessional.di.koin.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}