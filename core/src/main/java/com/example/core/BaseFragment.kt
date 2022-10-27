package com.example.core

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.module.AppState
import com.example.utils.OnlineLiveData

abstract class BaseFragment<T : AppState> : Fragment(), BackButtonClickListener {
    abstract fun renderData(appState: T)
    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToNetworkChange()
    }

    private fun subscribeToNetworkChange() {
        OnlineLiveData(requireContext()).observe(this, Observer<Boolean> {
            isNetworkAvailable = it
            if (!isNetworkAvailable) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_offline_network),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })
    }
}