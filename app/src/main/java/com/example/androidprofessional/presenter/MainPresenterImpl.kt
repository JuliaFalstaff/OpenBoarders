package com.example.androidprofessional.presenter

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.usecase.MainInteractor
import com.example.androidprofessional.model.repository.DataSourceLocal
import com.example.androidprofessional.model.repository.DataSourceRemote
import com.example.androidprofessional.model.repository.RepositoryImpl
import com.example.androidprofessional.rx.SchedulerProvider
import com.example.androidprofessional.view.Contract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver


class MainPresenterImpl<T : AppState, V : Contract.View>(
        private val interactor: MainInteractor = MainInteractor(
                RepositoryImpl(DataSourceRemote()),
                RepositoryImpl(DataSourceLocal())),
        protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
        protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
        ) : Contract.Presenter<T, V> {
    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.addAll(
                interactor.getData(word, isOnline)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.main())
                        .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                        .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(t: AppState) {
                currentView?.renderData(t)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {}
        }
    }
}