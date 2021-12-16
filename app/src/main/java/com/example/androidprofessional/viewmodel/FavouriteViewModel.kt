package com.example.androidprofessional.viewmodel

import com.example.androidprofessional.usecase.FavouriteInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavouriteViewModel(private val interactor: FavouriteInteractor) :
    com.example.core.BaseViewModel<com.example.module.AppState>() {

    private var job: Job? = null

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(com.example.module.AppState.Error(error))
    }

    fun getData() {
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            liveDataForViewToObserve.postValue(interactor.getFavouritesData())
        }
    }

    override fun onCleared() {
        liveDataForViewToObserve.postValue(com.example.module.AppState.SuccessHistoryData(null))
        super.onCleared()
    }
}