package com.example.androidprofessional.viewmodel

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.usecase.FavouriteInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavouriteViewModel(private val interactor: FavouriteInteractor) : BaseViewModel<AppState>() {

    private var job: Job? = null

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    fun getData() {
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            liveDataForViewToObserve.postValue(interactor.getFavouritesData())
        }
    }

    override fun onCleared() {
        liveDataForViewToObserve.postValue(AppState.SuccessHistoryData(null))
        super.onCleared()
    }
}