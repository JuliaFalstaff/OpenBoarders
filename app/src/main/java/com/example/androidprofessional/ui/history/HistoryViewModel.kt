package com.example.androidprofessional.ui.history


import androidx.lifecycle.LiveData
import com.example.androidprofessional.usecase.history.HistoryInteractor
import com.example.core.BaseViewModel
import com.example.module.AppState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private var job: Job? = null

    override fun onCleared() {
        liveDataForViewToObserve.postValue(AppState.Success(null))
        super.onCleared()
    }

    override fun getHistoryData(): LiveData<AppState> {
        liveDataForViewToObserve.postValue(AppState.Loading)
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            liveDataForViewToObserve.postValue(interactor.getHistoryData())
        }
        return super.getHistoryData()
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }
}