package com.example.androidprofessional.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidprofessional.AppState
import com.example.androidprofessional.view.Contract

abstract class BaseActivity<T: AppState> : AppCompatActivity(), Contract.View {
    protected lateinit var presenter: Contract.Presenter <T, Contract.View>
    protected abstract fun createPresenter(): Contract.Presenter<T, Contract.View>
    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

}