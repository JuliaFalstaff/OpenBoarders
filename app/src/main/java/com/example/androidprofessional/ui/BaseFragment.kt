package com.example.androidprofessional.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.utils.isOnline
import com.example.androidprofessional.viewmodel.BaseViewModel

abstract class BaseFragment<T : AppState> : Fragment() {
    abstract fun renderData(appState: T)
    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNetworkAvailable = isOnline(context)
    }
}