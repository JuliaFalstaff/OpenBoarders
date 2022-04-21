package com.example.androidprofessional.navigation

import android.os.Bundle
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun detailedFragment(bundle: Bundle): Screen
    fun historyFragment(): Screen
    fun favouriteFragment(): Screen
    fun memoryCardsFragment(): Screen
    fun mainFragment(): Screen
    fun searchFragment(): Screen
}