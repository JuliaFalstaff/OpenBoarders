package com.example.androidprofessional.history


import com.example.core.BaseViewModel
import com.example.module.AppState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private var job: Job? = null

    override fun onCleared() {
        liveDataForViewToObserve.postValue(AppState.SuccessHistoryData(null))
        super.onCleared()
    }

    fun getData() {
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            liveDataForViewToObserve.postValue(interactor.getHistoryData())
        }
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }
}