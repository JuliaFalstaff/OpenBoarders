package com.example.androidprofessional.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initBottomNavigationView()
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }

    private fun initBottomNavigationView() {
        binding.bottomApiNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_bar_home -> {
                    openFragment(MainFragment())
                    true
                }
                R.id.bottom_bar_fav -> {
                    openFragment(com.example.favouritescreen.FavouriteFragment())
                    true
                }
                R.id.bottom_bar_history -> {
                    openFragment(com.example.historyscreen.HistoryFragment())
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

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}