package com.example.androidprofessional.navigation

import android.os.Bundle
import com.example.androidprofessional.ui.*
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun detailedFragment(bundle: Bundle): Screen {
        return FragmentScreen { DetailedInfoFragment.newInstance(bundle)}
    }

    override fun historyFragment(): Screen {
        return FragmentScreen { HistoryFragment.newInstance()}
    }

    override fun favouriteFragment(): Screen {
        return FragmentScreen {FavouriteFragment.newInstance()}
    }

    override fun memoryCardsFragment(): Screen {
        return FragmentScreen {MemoryCardsFragment.newInstance()}
    }

    override fun mainFragment(): Screen {
        return FragmentScreen { MainFragment.newInstance()}
    }

    override fun searchFragment(): Screen {
        return FragmentScreen {SearchDialogFragment.newInstance()}
    }
}