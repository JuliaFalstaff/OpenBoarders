package com.example.core

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.module.AppState
import com.example.utils.isOnline

abstract class BaseFragment<T : AppState> : Fragment(), BackButtonClickListener {
    abstract fun renderData(appState: T)
    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNetworkStatus()
    }

    private fun checkNetworkStatus() {
        isNetworkAvailable = isOnline(requireContext())
        if (!isNetworkAvailable) {
            Toast.makeText(
                requireContext(),
                getString(R.string.error_offline_network),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }
}