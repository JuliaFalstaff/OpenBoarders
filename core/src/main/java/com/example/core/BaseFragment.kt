package com.example.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.module.AppState
import com.example.utils.isOnline

abstract class BaseFragment<T : AppState> : Fragment() {
    abstract fun renderData(appState: T)
    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNetworkAvailable = isOnline(context)
    }
}