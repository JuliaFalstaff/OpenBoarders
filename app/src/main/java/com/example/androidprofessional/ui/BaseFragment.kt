package com.example.androidprofessional.ui

import androidx.fragment.app.Fragment
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.view.Contract

abstract class BaseFragment<T : AppState> : Fragment(), Contract.View {
    protected abstract fun createPresenter(): Contract.Presenter<T, Contract.View>
    abstract override fun renderData(appState: AppState)
    protected val presenter: Contract.Presenter<T, Contract.View> by lazy { createPresenter() }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}