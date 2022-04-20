package com.example.androidprofessional.ui

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
                    R.anim.slide_out)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setSplashScreenAnimation()
        } else {
            setTheme(R.style.AppTheme)
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()
        if (savedInstanceState == null) {
            router.replaceScreen(screens.mainFragment())
        }
    }

    @RequiresApi(31)
    private fun setSplashScreenAnimation() {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenProvider ->
            ObjectAnimator.ofFloat(
                    splashScreenProvider.view,
                    View.TRANSLATION_Y,
                    START_ANIMATION,
                    -splashScreenProvider.view.height.toFloat()
            ).apply {
                interpolator = AnticipateInterpolator()
                duration = SLIDE_UP_DURATION
                doOnEnd { splashScreenProvider.remove() }
            }.start()
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

    companion object {
        private const val SLIDE_UP_DURATION = 1500L
        private const val START_ANIMATION = 0f
    }
}