package com.example.androidprofessional.viewmodel

import androidx.lifecycle.LiveData
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.usecase.history.HistoryInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private var job: Job? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        return super.getData(word, isOnline)
    }

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