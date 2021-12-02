package com.example.androidprofessional.viewmodel

import androidx.lifecycle.LiveData
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.repository.DataSourceLocal
import com.example.androidprofessional.model.repository.DataSourceRemote
import com.example.androidprofessional.model.repository.RepositoryImpl
import com.example.androidprofessional.usecase.MainInteractor
import javax.inject.Inject

class MainViewModel @Inject constructor (private val interactor: MainInteractor): BaseViewModel<AppState>() {

    private var appState: AppState? = null
    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.addAll(
                interactor.getData(word, isOnline)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.main())
                        .doOnSubscribe { liveDataForViewToObserve.postValue(AppState.Loading(null)) }
                        .subscribe(
                                {state ->
                                    appState = state
                                    liveDataForViewToObserve.postValue(state)
                                },
                                {error -> liveDataForViewToObserve.postValue(AppState.Error(error))},
                                {})
        )
        return super.getData(word, isOnline)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}