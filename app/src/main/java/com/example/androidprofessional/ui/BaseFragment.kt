package com.example.androidprofessional.ui

import androidx.fragment.app.Fragment
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.viewmodel.BaseViewModel

abstract class BaseFragment<T : AppState> : Fragment() {
    abstract fun renderData(appState: T)
    abstract val model: BaseViewModel<T>
}