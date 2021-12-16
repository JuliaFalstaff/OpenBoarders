package com.example.historyscreen

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    com.example.core.BaseViewModel<com.example.module.AppState>() {

    private var job: Job? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<com.example.module.AppState> {
        return super.getData(word, isOnline)
    }

    override fun onCleared() {
        liveDataForViewToObserve.postValue(com.example.module.AppState.SuccessHistoryData(null))
        super.onCleared()
    }

    fun getData() {
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            liveDataForViewToObserve.postValue(interactor.getHistoryData())
        }
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(com.example.module.AppState.Error(error))
    }
}