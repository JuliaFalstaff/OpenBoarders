package com.example.androidprofessional.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.ActivityMainBinding
import com.example.androidprofessional.navigation.AndroidScreens
import com.example.core.BackButtonClickListener
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val screens: AndroidScreens by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private val router by inject<Router>()
    private val navigator = object : AppNavigator(this, R.id.container) {
        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment,
        ) {
            fragmentTransaction.setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()
        if (savedInstanceState == null) {
            router.replaceScreen(screens.mainFragment())
        }
    }

    private fun initBottomNavigationView() = with(binding) {
        bottomApiNavigationView.itemIconTintList = null
        bottomApiNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_bar_home -> {
                    router.navigateTo(screens.mainFragment())
                    true
                }
                R.id.bottom_bar_fav -> {
                    router.navigateTo(screens.favouriteFragment())
                    true
                }
                R.id.bottom_bar_history -> {
                    router.navigateTo(screens.historyFragment())
                    true
                }
                R.id.bottom_bar_fav_card -> {
                    router.navigateTo(screens.memoryCardsFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonClickListener && it.backPressed()) {
                return
            }
        }
        router.exit()
        super.onBackPressed()
    }
}